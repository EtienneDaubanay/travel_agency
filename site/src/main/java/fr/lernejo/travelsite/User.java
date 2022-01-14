package fr.lernejo.travelsite;

public class User {
    private String userEmail;
    private String userName;
    private String userCountry;
    private String weatherExpectation;
    private int minimumTemperatureDistance;

    public User() {
    }

    public User(String userEmail, String userName, String userCountry, String weatherExpectation, int minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public Boolean getWeatherExpectation() {

        Boolean flag = false;
        if(weatherExpectation.equals("WARMER")){
                flag = true;
        }
        return flag;
    }

    public void setWeatherExpectation(String weatherExpectation) {
        this.weatherExpectation = weatherExpectation;
    }

    public int getMinimumTemperatureDistance() {
        return minimumTemperatureDistance;
    }

    public void setMinimumTemperatureDistance(int minimumTemperatureDistance) {
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }

    @Override
    public String toString() {
        return "User{" +
            "userEmail='" + userEmail + '\'' +
            ", userName='" + userName + '\'' +
            ", userCountry='" + userCountry + '\'' +
            ", weatherExpectation=" + weatherExpectation +
            ", minimumTemperatureDistance=" + minimumTemperatureDistance +
            '}';
    }
}
