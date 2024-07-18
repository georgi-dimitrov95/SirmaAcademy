package HotelReservationSystem;

public class Room {
    String number;
    String type;
    String amenities;
    Double price;
    int maxOccupancy;
    int cancelFeePercent;
    boolean isAvailable;

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCancelFeePercent() {
        return cancelFeePercent;
    }

    public void setCancelFeePercent(int cancelFeePercent) {
        this.cancelFeePercent = cancelFeePercent;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}





// Create a file for room instances: e.g., Deluxe Room 101, Suite 201.
//Attributes for each room should include:
        //Room number
        //Type (e.g., Deluxe, Suite)
        //Price per night
        //Cancellation fee
        //Status (e.g., available, booked)