import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Feladatok {
    List<Fuvar> fuvarok;
    public Feladatok(List<Fuvar> fuvarok) {
        this.fuvarok = fuvarok;
        System.out.println("*************************");
        System.out.println("*    1. feladat         *");
        System.out.println("*************************");
        System.out.println("Összesen: " + fuvarok.size() + "db utazás került feljegyzésre.");


        System.out.println("*************************");
        System.out.println("*     2.Feladat         *");
        System.out.println("*************************");
        List<Fuvar> taxi6185 = getFuvarById(6185);
        System.out.println("A 6185-ös taxi bevétele: " + getIncome(taxi6185) +
                "$\n Fuvarjai száma: " + taxi6185.size()
        );

        System.out.println("*************************");
        System.out.println("*     3.Feladat         *");
        System.out.println("*************************");
        System.out.printf("A taxisok összesen: %.2f mérföldet tettek meg.\n", getMiles(this.fuvarok));

        System.out.println("*************************");
        System.out.println("*     4.Feladat         *");
        System.out.println("*************************");
        System.out.printf("A leghosszabb fuvar adatai: %s" +
                "\n", getMax(this.fuvarok));

        Fuvar bestFuvar = getBestTip(this.fuvarok);

        System.out.println("*************************");
        System.out.println("*     5.Feladat         *");
        System.out.println("*************************");
        System.out.printf("A legbőkezűbb fuvar adatai: %s\n" +
                "Borravaló aránya: %.2f%%\n", bestFuvar, (bestFuvar.getBorravalo() / bestFuvar.getUtar()) * 100);


        List<Fuvar> taxi4261 = getFuvarById(4261);

        System.out.println("*************************");
        System.out.println("*     6.Feladat         *");
        System.out.println("*************************");
        System.out.printf("A 4261-es taxi összesen: %.2fkm-et tett meg.\n", getDistance(taxi4261) * 1.6);


        List<Fuvar> wrongFuvar = getWornFuvar(this.fuvarok);

        System.out.println("*************************");
        System.out.println("*     7.Feladat         *");
        System.out.println("*************************");
        System.out.printf("Rossz fuvarok száma: %ddb\n" +
                        "Összes időtartalma: %d sec\n" +
                        "Teljes bevétel: %.2f$\n", wrongFuvar.size(), getAllTravelTime(wrongFuvar),
                getIncome(wrongFuvar)
        );

        System.out.println("*************************");
        System.out.println("*     8.Feladat         *");
        System.out.println("*************************");
        if (isContains(1452, this.fuvarok)) {
            System.out.println("Tartalmazza a 1452-es azonosítójú taxit az állómány");
        } else {
            System.out.println("Nem tartalmazza a 1452-es azonosítójú taxit az állómány");
        }

        System.out.println("*************************");
        System.out.println("*     9.Feladat         *");
        System.out.println("*************************");
        Arrays.stream(getThreeShortestTime(this.fuvarok)).forEach(System.out::println);

        System.out.println("*************************");
        System.out.println("*     10.Feladat        *");
        System.out.println("*************************");
        System.out.println("December 24-én "+ getFuvarByDate(this.fuvarok, LocalDate.parse("2000-12-24")).size()
                +"db fuvar volt.");
    }

    private List<Fuvar> getFuvarById(int id) {
        List<Fuvar> fuvar = fuvarok.stream()
                .filter(f -> f.getId() == id)
                .collect(Collectors.toList());
        return fuvar;
    }

    private double getIncome(List<Fuvar> taxi) {
        Double tips = taxi.stream()
                .mapToDouble(Fuvar::getBorravalo)
                .sum();
        Double priceOfTrips = taxi.stream()
                .mapToDouble(Fuvar::getUtar)
                .sum();

        return tips + priceOfTrips;
    }

    private double getMiles(List<Fuvar> fuvarok) {
        double count = fuvarok.stream()
                .mapToDouble(Fuvar::getTavolsag)
                .sum();
        return count;
    }

    private Fuvar getMax(List<Fuvar> fuvarok) {
        Fuvar fuvar = fuvarok.stream()
                .max(Comparator.comparingInt(Fuvar::getUtazasiido))
                .get();
        return fuvar;
    }

    private Fuvar getBestTip(List<Fuvar> fuvarok) {
        Fuvar fuvar = fuvarok.stream()
                .max(Comparator.comparingDouble(trip -> trip.getBorravalo() / trip.getUtar()))
                .get();
        return fuvar;
    }

    private double getDistance(List<Fuvar> taxi) {
        return taxi.stream()
                .mapToDouble(Fuvar::getTavolsag)
                .sum();
    }

    private int getAllTravelTime(List<Fuvar> fuvarok) {
        return fuvarok.stream()
                .mapToInt(Fuvar::getUtazasiido)
                .sum();
    }

    private List<Fuvar> getWornFuvar(List<Fuvar> fuvarok) {
        List<Fuvar> wrong = fuvarok.stream()
                .filter(trip -> trip.getUtazasiido() > 0 && trip.getUtar() > 0
                        && trip.getTavolsag() == 0)
                .collect(Collectors.toList());
        return wrong;
    }

    private boolean isContains(int id, List<Fuvar> fuvarok) {
        return fuvarok.stream()
                .anyMatch(t -> t.getId() == id);
    }

    private Fuvar[] getThreeShortestTime(List<Fuvar> fuvarok) {
        List<Fuvar> filtered = fuvarok.stream()
                .filter(f -> f.getUtazasiido() != 0)
                .sorted(Comparator.comparingInt(Fuvar::getUtazasiido))
                .collect(Collectors.toList());
        Fuvar[] result = {filtered.get(0), filtered.get(1), filtered.get(2)};
        return result;
    }

    private List<Fuvar> getFuvarByDate(List<Fuvar> fuvarok, LocalDate date) {
        return fuvarok.stream()
                .filter(f -> f.getIndulas().getMonth().equals(date.getMonth()) &&
                        f.getIndulas().getDayOfMonth() == date.getDayOfMonth())
                .collect(Collectors.toList());
    }

}