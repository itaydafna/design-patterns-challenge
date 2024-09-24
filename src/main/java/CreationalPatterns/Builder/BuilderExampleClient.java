package CreationalPatterns.Builder;

class Dish {
    public Dish(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }

}

class MainDish extends Dish {
    public MainDish(String name) {
        super(name);
    }
}

class SideDish extends Dish {
    public SideDish(String name) {
        super(name);
    }
}

class Drink extends Dish {
    public Drink(String name) {
        super(name);
    }
}

class Dessert extends Dish {
    public Dessert(String name) {
        super(name);
    }
}

class VegMainDish extends MainDish {
    public VegMainDish(String name) {
        super(name);
    }
}

class NonVegMainDish extends MainDish {
    public NonVegMainDish(String name) {
        super(name);
    }
}

class VegSideDish extends SideDish {
    public VegSideDish(String name) {
        super(name);
    }
}

class NonVegSideDish extends SideDish {
    public NonVegSideDish(String name) {
        super(name);
    }
}


class Meal {
    private final MainDish mainDish;
    private final SideDish sideDish;
    private final Drink drink;
    private final Dessert dessert;

    public Meal(MainDish mainDish, SideDish sideDish, Drink drink, Dessert dessert) {
        this.mainDish = mainDish;
        this.sideDish = sideDish;
        this.drink = drink;
        this.dessert = dessert;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The meal consists of: \n");
        if (this.mainDish != null) {
            stringBuilder.append(this.mainDish.getName());
            stringBuilder.append("\n");
        }
        if (this.sideDish != null) {
            stringBuilder.append(this.sideDish.getName());
            stringBuilder.append("\n");
        }
        if (this.drink != null) {
            stringBuilder.append(this.drink.getName());
            stringBuilder.append("\n");
        }
        if (this.dessert != null) {
            stringBuilder.append(this.dessert.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

class VegetarianMeal extends Meal {
    public VegetarianMeal(VegMainDish mainDish, VegSideDish sideDish, Drink drink, Dessert dessert) {
        super(mainDish, sideDish, drink, dessert);
    }
}

class NonVegetarianMeal extends Meal {
    public NonVegetarianMeal(NonVegMainDish mainDish, NonVegSideDish sideDish, Drink drink, Dessert dessert) {
        super(mainDish, sideDish, drink, dessert);
    }
}


interface MealBuilder<M extends MainDish, S extends SideDish> {
    MealBuilder<M, S> setMainDish(M mainDish);

    MealBuilder<M, S> setSideDish(S sideDish);

    MealBuilder<M, S> setDrink(Drink drink);

    MealBuilder<M, S> setDessert(Dessert dessert);

    Meal build();


}

class VegetarianMealBuilder implements MealBuilder<VegMainDish, VegSideDish> {
    private VegMainDish mainDish;
    private VegSideDish sideDish;
    private Drink drink;
    private Dessert dessert;


    @Override
    public MealBuilder<VegMainDish,VegSideDish> setMainDish(VegMainDish mainDish) {
        this.mainDish = mainDish;
        return this;

    }

    @Override
    public MealBuilder<VegMainDish,VegSideDish> setSideDish(VegSideDish sideDish) {
        this.sideDish = sideDish;
        return this;
    }

    @Override
    public MealBuilder<VegMainDish,VegSideDish> setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    @Override
    public MealBuilder<VegMainDish,VegSideDish> setDessert(Dessert dessert) {
        this.dessert = dessert;
        return this;

    }

    @Override
    public Meal build() {
        return new VegetarianMeal(mainDish, sideDish, drink, dessert);
    }
}


class NonVegetarianMealBuilder implements MealBuilder<NonVegMainDish, NonVegSideDish> {
    private NonVegMainDish mainDish;
    private NonVegSideDish sideDish;
    private Drink drink;
    private Dessert dessert;


    @Override
    public MealBuilder<NonVegMainDish,NonVegSideDish> setMainDish(NonVegMainDish mainDish) {
            this.mainDish = mainDish;
            return this;
    }

    @Override
    public MealBuilder<NonVegMainDish,NonVegSideDish> setSideDish(NonVegSideDish sideDish) {
            this.sideDish = sideDish;
            return this;
    }

    @Override
    public MealBuilder<NonVegMainDish,NonVegSideDish> setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    @Override
    public MealBuilder<NonVegMainDish,NonVegSideDish> setDessert(Dessert dessert) {
        this.dessert = dessert;
        return this;

    }

    @Override
    public Meal build() {
        return new NonVegetarianMeal(mainDish, sideDish, drink, dessert);
    }
}

class MealBuilderDirector {
    void constructBasicIndianVegetarianMeal(MealBuilder<VegMainDish, VegSideDish> mealBuilder) {
        VegMainDish vegMainDish = new VegMainDish("Palak Paneer");
        Drink drink = new Drink("Coconut watter");

        mealBuilder.setMainDish(vegMainDish);
        mealBuilder.setDrink(drink);

    }

    void constructFullItalianVegetarianMeal(MealBuilder<VegMainDish, VegSideDish> mealBuilder) {
        VegMainDish vegMainDish = new VegMainDish("Pasta with tomato sauce");
        Drink drink = new Drink("Sparkling watter");
        VegSideDish vegSideDish = new VegSideDish("Steamed Broccoli");
        Dessert vegDessert = new Dessert("Tahini ice-cream ");

        mealBuilder.setMainDish(vegMainDish).setDrink(drink).setSideDish(vegSideDish).setDessert(vegDessert);

    }

    void constructFastFoodMeal(MealBuilder<NonVegMainDish, NonVegSideDish> nonVegetarianMealBuilder) {
        NonVegMainDish nonVegMainDish = new NonVegMainDish("Burger");
        NonVegSideDish nonVegSideDish = new NonVegSideDish("Fries");
        Drink drink = new Drink("Cocke");

        nonVegetarianMealBuilder.setMainDish(nonVegMainDish).setSideDish(nonVegSideDish).setDrink(drink);

    }

    void constructCoffeeShopDeal(MealBuilder<VegMainDish,VegSideDish> mealBuilder) {
        Drink drink = new Drink("Filter Coffee");
        Dessert nonDessert = new Dessert("Apple pie");
        mealBuilder.setDrink(drink).setDessert(nonDessert);
    }
}


public class BuilderExampleClient {
    public static void main(String[] args) {
        VegetarianMealBuilder basicIndianMealBuilder = new VegetarianMealBuilder();
        VegetarianMealBuilder fullItalianVegetarianMealBuilder = new VegetarianMealBuilder();
        NonVegetarianMealBuilder fastFoodMealBuilder = new NonVegetarianMealBuilder();
        VegetarianMealBuilder coffeeShopDealBuilder = new VegetarianMealBuilder();

        MealBuilderDirector mealBuilderDirector = new MealBuilderDirector();

        mealBuilderDirector.constructBasicIndianVegetarianMeal(basicIndianMealBuilder);
        mealBuilderDirector.constructFullItalianVegetarianMeal(fullItalianVegetarianMealBuilder);
        mealBuilderDirector.constructFastFoodMeal(fastFoodMealBuilder);
        mealBuilderDirector.constructCoffeeShopDeal(coffeeShopDealBuilder);

        System.out.println("Building Basic Indian Meal..");
        System.out.println(basicIndianMealBuilder.build().toString());
        System.out.println("Building Full Italian Vegetarian Meal...");
        System.out.println(fullItalianVegetarianMealBuilder.build().toString());
        System.out.println("Building Fast Food Meal...");
        System.out.println(fastFoodMealBuilder.build().toString());
        System.out.println("Building Coffee shop Deal...");
        System.out.println(coffeeShopDealBuilder.build().toString());
    }

}
