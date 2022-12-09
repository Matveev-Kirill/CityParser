import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;


public class CityPars {
    /**
     * Метод, использующий класс Scanner 
     * 
     * @param   filePath  Путь до csv файла с данными
     * @return            Возвращает List<City>
     */
    public static List<City> initToParse(String filePath){
        
        List<City> cities = new ArrayList<>();
        try{
            //Для корректной работы указываетя кодировка utf-8
            Scanner scanner = new Scanner(new File(filePath),"utf-8");
            System.setProperty("console.encoding","utf-8");
            
            while(scanner.hasNextLine()){
                cities.add(parse(scanner.nextLine()));       
            }
            scanner.close();
            
        }catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return cities;
    }
    /**
     * Метод, реализующий обработку каждой строки из csv
     * 
     * @param   line  Строка из csv файла
     * @return        Новый объект класса City
     */
    private static City parse(String line){
        Scanner scanner = new Scanner(line);
        //Делим поступающую строку на 6 частей, включая номер строки
        String[] items = scanner.nextLine().split(";",6);
        
        if(items[5].isEmpty()){
            items[5] = null;
        }
        scanner.close();
        return new City(items[1],items[2],items[3],Integer.parseInt(items[4]),items[5]);
      
    }
    /**
     * Реализация сортировки по названию города при помощи lambda-выражения без учета регистра 
     * 
     * @param cities  Объект типа City
     */
    public static void sortByCityName(List<City> cities){
        cities.sort((name1,name2) -> name1.getCityName().compareToIgnoreCase(name2.getCityName()));
    }
     /**
     * Сортировка массива городов по наименованию в алфавитном порядке по убыванию
     * без учета регистра, используя {@link java.util.Comparator}
     *
     * @param cities массив городов
     */
    public static void sortByCityNameV2(List<City> cities) {
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o1.getCityName().compareToIgnoreCase(o2.getCityName());
            }
        });
    }
    /**
     * Реализация сортировки по федеральному округу и названию города в нем с учетом регистра
     * 
     * @param cities  Объект типа City
     */
    public static void sortByCityNameFederalName(List<City> cities){
        cities.sort(Comparator.comparing(City::getFederalName).thenComparing(City::getCityName));
    }
    /**
     * Преобразование списка городов в массив и поиск максимального элемента и его индекса
     * 
     * @param  cities   Объект типа City
     * @return          [<индекс максимального элемента>] = максимальный элемент
     */
    public static void maxValueV1(List<City> cities){
        City[] newCities = cities.toArray(new City[0]);
        int maxValue = 0;
        int index = 0;
        for(int i = 0; i < newCities.length;i++){
            if(newCities[i].getPopulation() > maxValue){
                maxValue = newCities[i].getPopulation();
                index = i;
            }
        }
        System.out.println("["+ index + "] = " + maxValue);
    }
    /**
     * Выделение столбца из объекта типо City и поиск максимального элемента с его индексом без преобразование City в массив
     * 
     * @param  cities   Объект типа City
     * @return          [<индекс максимального элемента>] = максимальный элемент
     */
    public static void maxValueV2(List<City> cities){
        Integer[] peoples = cities.stream().map(population -> population.getPopulation()).toArray(Integer[]::new);
        int maxElem = cities.stream().map(City::getPopulation).mapToInt(i -> i).max().getAsInt();
        int index = Arrays.asList(peoples).indexOf(maxElem);
        System.out.println("["+ index + "] = " + maxElem);
    }
    /**
     * Поиск количества городов в разрезе регионов
     * 
     * @param cities    Объект типа City
     */
    public static void uniqueCitiesInRegion(List<City> cities){
        
       List<String> regions = cities.stream().map(region -> region.getRegionName()).toList();
       Map<String, Long> uniqReg = regions.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
       for(String key : uniqReg.keySet()){
        System.out.println(key + " - " + uniqReg.get(key));
       }
    }

     /**
     * Поиск количества городов в каждом из регионов (old school)
     *
     * @param cities Объект типа City
     */
    public static void findCountCityByRegionV1(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        for (City city : cities) {
            if (!regions.containsKey(city.getRegionName())) {
                regions.put(city.getRegionName(), 1);
            } else {
                regions.put(city.getRegionName(), regions.get(city.getRegionName()) + 1);
            }
        }
        for (String key : regions.keySet()) {
            System.out.println(MessageFormat.format(" {0} - {1}", key, regions.get(key)));
        }
    }
    
     /**
     * Поиск количества городов в каждом из регионов (lambda-выражения)
     *
     * @param cities массив городов
     */
    public static void findCountCityByRegionV2(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegionName(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" {0} - {1}", k, v)));
    }
}
