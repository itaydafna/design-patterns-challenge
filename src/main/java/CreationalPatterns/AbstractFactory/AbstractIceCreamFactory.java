package CreationalPatterns.AbstractFactory;


abstract class ChocolateFlavourIceCream {
    public abstract String toString();
}

abstract class VanillaFlavourIceCream {
    public abstract String toString();
}

class BelgianChocolate extends ChocolateFlavourIceCream {

    @Override
    public String toString() {
        return "Belgian Chocolate";
    }
}

class ChocolateFudgeBrownie extends ChocolateFlavourIceCream {

    @Override
    public String toString() {
        return "Chocolate Fudge Brownie";
    }
}

class VanillaBean extends VanillaFlavourIceCream {

    @Override
    public String toString() {
        return "Vanilla Bean";
    }
}

class BenAndJerrysVanilla extends VanillaFlavourIceCream {
    @Override
    public String toString() {
        return "Ben And Jerry's Vanilla";
    }
}


class BenAndJerrysIceCreamFactory extends AbstractIceCreamFactory {

    @Override
    public ChocolateFlavourIceCream makeChocolateFlavourIceCream() {
        return new ChocolateFudgeBrownie();
    }

    @Override
    public VanillaFlavourIceCream makeVanillaFlavourIceCream() {
        return new BenAndJerrysVanilla();
    }
}

class HaagenDazsIceCreamFactory extends AbstractIceCreamFactory {
    @Override
    public ChocolateFlavourIceCream makeChocolateFlavourIceCream() {
        return new BelgianChocolate();
    }

    @Override
    public VanillaFlavourIceCream makeVanillaFlavourIceCream() {
        return new VanillaBean();
    }
}

enum Brand {
    BEN_AND_JERRYS,
    HAAGEN_DAZS
}


abstract class AbstractIceCreamFactory {

    private static final BenAndJerrysIceCreamFactory BEN_AND_JERRYS_ICE_CREAM_FACTORY = new BenAndJerrysIceCreamFactory();
    private static final HaagenDazsIceCreamFactory HAAGEN_DAZS_ICE_CREAM_FACTORY = new HaagenDazsIceCreamFactory();

    static AbstractIceCreamFactory getFactory(Brand brand) {
        return switch (brand) {
            case BEN_AND_JERRYS -> BEN_AND_JERRYS_ICE_CREAM_FACTORY;
            case HAAGEN_DAZS -> HAAGEN_DAZS_ICE_CREAM_FACTORY;
        };
    }

    public abstract ChocolateFlavourIceCream makeChocolateFlavourIceCream();

    public abstract VanillaFlavourIceCream makeVanillaFlavourIceCream();

    public void makeChocolateVanillaCone() {
        System.out.println("1.Grab cone");
        System.out.println("2.Add one scoop of " + makeChocolateFlavourIceCream().toString());
        System.out.println("3.Add one scoop of " + makeVanillaFlavourIceCream().toString());
        System.out.println("Bon appetit!");

    }

}

class Client {
    public static void main(String[] args) {
        AbstractIceCreamFactory factory = AbstractIceCreamFactory.getFactory(Brand.HAAGEN_DAZS);
        factory.makeChocolateVanillaCone();
    }

}


