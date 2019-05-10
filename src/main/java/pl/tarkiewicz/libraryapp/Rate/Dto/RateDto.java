package pl.tarkiewicz.libraryapp.Rate.Dto;


public class RateDto {

    public RateDto() {
    }

//    public RateDto(int rate) {
//        this.rate = rate;
//    }
//
//    private int rate;
//
//    public int getRate() {
//        return rate;
//    }
//
//    public void setRate(int rate) {
//        this.rate = rate;
//    }

    //private Double rate;
//
//
//    public RateDto(Double rate) {
//        this.rate = rate;
//
//    }
//
//    public Double getRate() {
//        return rate;
//    }
//
//    public void setRate(Double rate) {
//        this.rate = rate;
//    }

    private String rate;

    public RateDto(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
