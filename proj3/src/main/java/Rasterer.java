import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        Map<String, Object> results = new HashMap<>();
        double ullon = params.get("ullon");
        double lrlon = params.get("lrlon");
        double ullat = params.get("ullat");
        double lrlat = params.get("lrlat");
        double width = params.get("w");
        double length = params.get("h");
        double box_londpp = lonDPP(ullon, lrlon, width);
        int depth = depth(box_londpp);
        int ImgX = firstImgX(depth, ullon);
        int ImgY = firstImgY(depth, ullat);
        double[] upperLeftImg = bounds(depth, ImgX, ImgY);
        int[] dimensions = dimensions(depth, lrlon, lrlat, ImgX, ImgY);
        int rows = dimensions[0];
        int columns = dimensions[1];
        double[] lowerRightImg = bounds(depth, ImgX + columns - 1, ImgY + rows - 1);
        double raster_ullon = upperLeftImg[0];
        double raster_ullat = upperLeftImg[1];
        double raster_lrlon = lowerRightImg[2];
        double raster_lrlat = lowerRightImg[3];

        String[][] images = new String[rows][columns];
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < columns; ++c) {
                images[r][c] = String.format("d%d_x%d_y%d.png", depth, c + ImgX, r + ImgY);
            }
        }

        results.put("raster_ul_lon", raster_ullon);
        results.put("raster_ul_lat", raster_ullat);
        results.put("raster_lr_lon", raster_lrlon);
        results.put("raster_lr_lat", raster_lrlat);
        results.put("render_grid", images);
        results.put("depth", depth);
        results.put("query_success", true);

        return results;
    }

    /** computes the longitudinal distance for each image at depth lvl. */
    private double deltaX(int lvl) {
        return Math.abs(MapServer.ROOT_ULLON - MapServer.ROOT_LRLON) / (Math.pow(2, lvl));
    }

    /** computes the latitudinal distance for each image at depth lvl. */
    private double deltaY(int lvl) {
        return Math.abs(MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT) / (Math.pow(2, lvl));
    }


    /** Return the coordinates of two points for the img d(lvl)_Xx_Yy.
     *
     * @param lvl - depth of the image (ranges from 0 to 7)
     * @param x - The number besides the x in the image name.
     * @param y - The number besides the y in the image name.
     * @return - array corresponding the bounds of the image
     *           where index 0 and 1 represents the x, y coordinates for
     *           the upperleft bound of the image while index 2,3 for the
     *           lower right bounding point.
     */
    private double[] bounds(int lvl, int x, int y) {
        double dx = deltaX(lvl);
        double dy = deltaY(lvl);
        double[] coordinates = new double[4];
        coordinates[0] = MapServer.ROOT_ULLON + (dx * x);        // x coordinate of the upper left point
        coordinates[1] = MapServer.ROOT_ULLAT - (dy * y);        // y coordinate of the upper left point
        coordinates[2] = MapServer.ROOT_ULLON + (dx * (x + 1));  // x coordinate of the lower right point
        coordinates[3] = MapServer.ROOT_ULLAT - (dy * (y + 1));  // y coordinate of the lower right point

        return coordinates;
    }

    /** Returns the longitudinal distance per pixel for the images
     *  of the given upper left longitude, lower right longitude and width. */
    private double lonDPP(double ullon, double lrlon, double w) {
        return Math.abs(ullon - lrlon) / w;
    }

    /** Returns the longitudinal distance per pixel for the images
     *  of size MapServer.TILE_SIZE. of depth lvl. */
    private double lonDPP(int lvl) {
       double[] bounding_points = bounds(lvl, 0, 0);
       double lrlon = bounding_points[2];
       double ullon = bounding_points[0];

       return Math.abs(ullon - lrlon) / MapServer.TILE_SIZE;
    }

    /** Returns the appropriate depth (i.e. zoom level) given the
     *  query box's lonDPP. */
    private int depth(double box_lonDPP) {
        int d = 0;
        double londpp = lonDPP(d);

        while(londpp > box_lonDPP && d < 7) {
            d++;
            londpp = lonDPP(d);
        }

        return d;
    }

    /** Returns an array containing 2 elements. The 0th element is
     *  the row and the second is the number of columns.
     */
    private int[] dimensions(int D, double lrlon, double lrlat,
                             int x, int y) {
        int row = 0;
        int col = 0;
        double dx = deltaX(D);
        double dy = deltaY(D);
        double lrX = bounds(D, x, y)[2];
        double lrY = bounds(D, x, y)[3];
        while(lrX + dx * col < lrlon && lrX + dx * col < MapServer.ROOT_LRLON) {
            col++;
        }

        while(lrY - dy*row > lrlat && lrY + dy * row > MapServer.ROOT_LRLAT) {
            row++;
        }


        int[] answer = new int[2];
        answer[0] = ++row;
        answer[1] = ++col;

        return answer;
    }

    /** Returns the number that goes beside the x in the image name.
     *  (e.g. d3_x2_y4  the number beside x that we mean is here 2).
     * @param D - the calculated depth
     * @param ullon - the query box's ullon (upper left longitude)
     */
    private int firstImgX(int D, double ullon) {
        double dx = deltaX(D);
        return (int)Math.floor(Math.abs(MapServer.ROOT_ULLON - ullon) / dx);
    }

    /** Returns the number that goes beside the y in the image name.
     *  (e.g. d3_x2_y4  the number beside y that we mean is here 4).
     * @param D - the calculated depth
     * @param ullat - the query box's ullat (upper left latitude)
     */
    private int firstImgY(int D, double ullat) {
        double dy = deltaY(D);
        return (int)Math.floor(Math.abs(MapServer.ROOT_ULLAT - ullat) / dy);
    }

}
