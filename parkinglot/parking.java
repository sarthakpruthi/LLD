

//Parking
//        ParkingSpot[]
//        Map<Integer, Vehicle> freeVehicleCount;
//        Map<String, ParkingSpot> //spotid, parking spot
//        Map<String, String> //vehicleNumber, spot id
//        Map<String, String> //ticketId, spot id
//
////archived
//        Map<String, String> //vehicle number, spot id
//        Map<String, String> //ticketId, spot id
//    Vehicle
//            int twowheelerFreeSpot;
//            int fourwheelerFreeSpot;
//            ParkingSpot
//            spotId - "0-3-1"
//            status
//            vehicleNumber
//            ticketId
//            vehicleType -2,4
//
//            Parkingmanager
//            Parking
//
//            ParkingSpot[][][]
//
//            Parking
//            Map<Integer, Floor>
//    Floor
//            Spot[][]
//            int free2Count;
//            int free4Count;
//            Spot
//            spotId - "0-3-1"
//            status
//            vehicleNumber
//            ticketId
//            vehicleType
//package org.example;
//
//import java.util.HashMap;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class parking {
//
//    private ParkingFloor[] floors;
//    private SearchManager searchManager;
//    private int[] vehicleTypes={2,4};
//    public parking(){}
//
//    /** use helper.print() and helper.println() for logging
//     normal System.out.println() logs won't appear
//     */
//    public void init( String[][][] parking) {
//        floors=new ParkingFloor[parking.length];
//        for(int i=0;i<parking.length;i++)
//            floors[i]=new ParkingFloor(i, parking[i],vehicleTypes);
//        searchManager= new SearchManager();
//    }
//
//    /**
//     * choose any parking spot of your own choice
//     * assign an empty parking spot to vehicle and
//     * map vehicleNumber and ticketId to the assigned spotId
//     *
//     * ParkingResult status 201 for success,
//     * 404 : for bad request like invalid input parameters vehicle type not found or
//     * both of vehicleNumber and ticketId are blank.
//     *
//     * vehicleType = 2 or 4 for 2-wheeler or 4-wheeler vehicle
//     */
//    public ParkingResult park(int vehicleType,
//                              String vehicleNumber, String ticketId){
//        for(ParkingFloor floor: floors){
//            ParkingResult result=floor.park(
//                    vehicleType,vehicleNumber, ticketId);
//            if(result!=null && result.getStatus()==201){
//                searchManager.index(result);
//                return result;
//            }
//        }
//        return new ParkingResult(
//                404, "", vehicleNumber, ticketId);
//    }
//
//    /**
//     * This method un-parks a vehicle
//     * return status types based on ParkingResult
//     * - 201 success, 404 : vehicle not found or any other error,
//     * - exactly one of spotId, vehicleNumber or ticketId will be non empty
//     * - spotId will be of the format "floor-row-column" and will be 0 based index
//     *  e.g 0-4-11 : ParkingSpot on floor 0, row 4 and column 11,
//     */
//    public int removeVehicle(String spotId,
//                             String vehicleNumber, String ticketId){
//        // extracting floor, row, column of parking spot where vehicle is parked
//        ParkingResult search = searchVehicle(spotId, vehicleNumber, ticketId);
//        if(search==null||search.getStatus()>=400)return 404;
//        Integer []location=helper.getSpotLocation(search.getSpotId());
//        int floor= location[0], row=location[1],col=location[2];
//        return floors[floor].removeVehicle(row,col);
//    }
//
//    /** floor is 0-index based, i.e.  0<=floor<parking.length
//     vehicleType = 2 or 4 for 2-wheeler or 4-wheeler vehicle
//     */
//    public int getFreeSpotsCount(int floor, int vehicleType){
//        if(floor<0||floor>=floors.length) return 0;
//        return floors[floor].getFreeSpotsCount(vehicleType);
//    }
//
//    /**
//     * status = 200 : success, 404 : not found
//     * exactly one of spotId, vehicleNumber or ticketId will be non empty
//     */
//    public ParkingResult searchVehicle(String spotId,
//                                       String vehicleNumber, String ticketId){
//        return searchManager.searchVehicle(
//                spotId, vehicleNumber, ticketId);
//    }
//
//}
//
//class ParkingFloor {
//    // vehicleType vs free spots count
//    private HashMap<Integer, AtomicInteger> freeSpotsCount;
//    private ParkingSpot parkingSpots[][];
//
//    ParkingFloor(int floor, String parkingFloor[][],
//                 int[] vehicleTypes, Helper01 helper){
//        parkingSpots = new ParkingSpot[parkingFloor.length][parkingFloor[0].length];
//        freeSpotsCount=new HashMap<>();
//
//        for(int vehicleType: vehicleTypes)
//            freeSpotsCount.put(vehicleType, new AtomicInteger(0));
//
//        for(int row=0;row<parkingFloor.length;row++){
//            for(int col=0;col<parkingFloor[row].length;col++)
//                // "2-1" and "4-1"
//                if(parkingFloor[row][col].endsWith("1")){
//                    int vehicleType=Integer.parseInt(
//                            parkingFloor[row][col].split("-")[0]);
//                    parkingSpots[row][col]=new ParkingSpot(   //0-8-4
//                            helper.getSpotId(floor, row, col), vehicleType);
//                    freeSpotsCount.get(vehicleType).addAndGet(1);
//                }
//        }
//    }
//
//    int getFreeSpotsCount(int vehicleType){
//        return freeSpotsCount.get(vehicleType).get();
//    }
//
//    /**
//     * This method un-parks a vehicle
//     * return status types based on ParkingResult
//     * - 201 success, 404 : vehicle not found or any other error,
//     */
//    public synchronized int removeVehicle(
//            int row, int col){
//        if(row<0
//                ||row>= parkingSpots.length||col<0
//                ||col>= parkingSpots[0].length
//                || !parkingSpots[row][col].isParked())
//            return 404;
//        parkingSpots[row][col].removeVehicle();
//        freeSpotsCount
//                .get(parkingSpots[row][col]
//                        .getVehicleType())
//                .addAndGet(1);
//        return 201;
//    }
//
//    public synchronized ParkingResult park(
//            int vehicleType, String vehicleNumber, String ticketId){
//        if(freeSpotsCount.get(vehicleType).get()==0)
//            return new ParkingResult(404,
//                    "", vehicleNumber, ticketId);
//        for(int row=0;row<parkingSpots.length;row++) {
//            for (ParkingSpot spot : parkingSpots[row]) {
//                if (spot != null && !spot.isParked()
//                        && spot.getVehicleType() == vehicleType) {
//                    freeSpotsCount.get(vehicleType).addAndGet(-1);
//                    spot.parkVehicle();
//                    return new ParkingResult(201,
//                            spot.getSpotId(), vehicleNumber, ticketId);
//                }
//            }
//        }
//        return new ParkingResult(404,
//                "", vehicleNumber, ticketId);
//    }
//}
//
//class ParkingSpot{
//    private String spotId;
//    private int vehicleType;
//    private boolean isParked;
//
//    ParkingSpot(String spotId, int vehicleType){
//        this.spotId=spotId;
//        this.vehicleType=vehicleType;
//        isParked=false;
//    }
//    boolean isParked(){
//        return isParked;
//    }
//    void parkVehicle(){
//        isParked=true;
//    }
//    void removeVehicle(){
//        isParked=false;
//    }
//    public String getSpotId() {
//        return spotId;
//    }
//    public int getVehicleType() {
//        return vehicleType;
//    }
//}
//
//class SearchManager{
//    private ConcurrentHashMap<String, ParkingResult> cache
//            = new ConcurrentHashMap<>();
//
//    public ParkingResult searchVehicle(String spotId,
//                                       String vehicleNumber, String ticketId){
//
//        if(spotId.trim().length()>0)
//            return cache.get(spotId);
//        if(vehicleNumber.trim().length()>0)
//            return cache.get(vehicleNumber);
//        if(ticketId.trim().length()>0)
//            return cache.get(ticketId);
//        return new ParkingResult(
//                404, spotId, vehicleNumber, ticketId);
//    }
//
//    void index(ParkingResult result){
//        cache.put(result.getSpotId(), result);
//        cache.put(result.getVehicleNumber(), result);
//        cache.put(result.getTicketId(), result);
//    }
//
//
//
//}
//
//
///**
// class ParkingResult{
// private int status;
// private String spotId, vehicleNumber, ticketId;
//
// ParkingResult(int status, String spotId,
// String vehicleNumber, String ticketId){
// this.status=status;
// this.spotId=spotId;
// this.vehicleNumber=vehicleNumber;
// this.ticketId=ticketId;
// }
//
// public int getStatus(){
// return status;
// }
//
// public String getSpotId(){
// return spotId;
// }
//
// public String getVehicleNumber(){
// return vehicleNumber;
// }
//
// public String getTicketId(){
// return ticketId;
// }
//
// @Override public boolean equals(Object obj) {
// if(obj==null) return false;
// ParkingResult result=(ParkingResult) obj;
// return  result.getSpotId().equals(spotId)
// && result.getVehicleNumber().equals(vehicleNumber)
// && result.getTicketId().equals(ticketId);
// }
//
// public String toString(){
// return "[Spot : "+spotId+", vehicle id : "
// +vehicleNumber+", ticket id : "+ticketId+"]";
// }
// }
//
// interface Q001ParkingLotInterface {
// void init(Helper01 helper, String [][][] parking);
// ParkingResult park(int vehicleType, String vehicleNumber, String ticketId);
// int removeVehicle(String spotId, String vehicleNumber, String ticketId);
// ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId);
// int getFreeSpotsCount(int floor, int vehicleType);
// }
//
// class Helper01{
// void print(String s){System.out.print(s);} void println(String s){print(s+"\n");}
// String getSpotId(int floor, int row, int column) {return ""+floor + "-" + row + "-" + column;}
// Integer[] getSpotLocation(String spotId) {Integer[] location = {-1, -1, -1};String[] ss = spotId.split("-");
// for (int i = 0; i < 3 && i < ss.length; i++) {location[i] = Integer.parseInt(ss[i]);}return location;}
// }
//
//
// v1 -> 0-2-0
//
// s>0-2-0 -> PR1
// v>bh123 -> PR1
// t>t1 -> PR1
//
// s>0-3-1 -> PR2
// v>bh123 -> PR2
// t>t2 -> PR2
//
//
// */
