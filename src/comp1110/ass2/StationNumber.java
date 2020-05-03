package comp1110.ass2;

/**
 * Enum type to get the coordinate of corresponding start tile for each start station
 *
 * @author Jiawei Fan
 */
public enum StationNumber {
    STATION1(1, "07"),
    STATION2(2, "06"),
    STATION3(3, "05"),
    STATION4(4, "04"),
    STATION5(5, "03"),
    STATION6(6, "02"),
    STATION7(7, "01"),
    STATION8(8, "00"),
    STATION9(9, "00"),
    STATION10(10, "10"),
    STATION11(11, "20"),
    STATION12(12, "30"),
    STATION13(13, "40"),
    STATION14(14, "50"),
    STATION15(15, "60"),
    STATION16(16, "70"),
    STATION17(17, "70"),
    STATION18(18, "71"),
    STATION19(19, "72"),
    STATION20(20, "73"),
    STATION21(21, "74"),
    STATION22(22, "75"),
    STATION23(23, "76"),
    STATION24(24, "77"),
    STATION25(25, "77"),
    STATION26(26, "67"),
    STATION27(27, "57"),
    STATION28(28, "47"),
    STATION29(29, "37"),
    STATION30(30, "27"),
    STATION31(31, "17"),
    STATION32(32, "07");

    private String stationCoordinate;
    private int stationNumber;

    StationNumber(int stationNumber, String stationCoordinate) {
        this.stationNumber = stationNumber;
        this.stationCoordinate = stationCoordinate;
    }

    /**
     * get the coordinates of the start tile of a station
     *
     * @param stationNumber a int representating the station number
     * @return a string of length two representing the coordinates of the corresponding start tile
     * @author Jiawei Fan
     */
    public static String fromStationNumber(int stationNumber) {
        for (StationNumber a : StationNumber.values()) {
            if (a.stationNumber == stationNumber) {
                return a.stationCoordinate;
            }
        }
        return "";
    }
}