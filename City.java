public class City {
    
  
    private String cityName;
    private String regionName;
    private String federalName;
    private Integer population;
    private String year;

    /**
     * Конструктор класса City
     * 
     * @param cityName     Наименование города
     * @param regionName   Наименования региона
     * @param federalName  Наименование федерального округа
     * @param population   Количество жителей города
     * @param year         Первое упоминание или дата основания
     */
    public City(String cityName, String regionName, String federalName, Integer population, String year){

        this.cityName = cityName;
        this.regionName = regionName;
        this.federalName = federalName;
        this.population = population;
        this.year = year;
        
    }

 
     public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getFederalName() {
        return federalName;
    }

    public void setFederalName(String federalName) {
        this.federalName = federalName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "City{name = '" + getCityName() + "', region = '" + getRegionName() + "', district = '" + getFederalName() + "', population = " + getPopulation() + ", foundation = '" + getYear() + "'}";
    }
}
