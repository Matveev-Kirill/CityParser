
import java.util.ArrayList;
import java.util.List;


public class Main{

    public static void main(String[] args) {
        
        List<City> cities = new ArrayList<>();

        String filePath = "city_ru.csv";

        cities = CityPars.initToParse(filePath);
        //Реализация сортировки только по имени города через lambda-выражения
        //CityPars.sortByCityName(cities);

        //Реализация сортировки только по имени города через Comparator
        //CityPars.sortByCityNameV2(cities);

        //Реализация сортировки по имени города и федеральному округу
        //CityPars.sortByCityNameFederalName(cities);
        //cities.forEach(System.out::println);
        
        //CityPars.maxValueV1(cities);
        //CityPars.maxValueV2(cities);
        
        //CityPars.uniqueCitiesInRegion(cities);
        //CityPars.findCountCityByRegionV1(cities);
        CityPars.findCountCityByRegionV2(cities);

    


       
    }
}