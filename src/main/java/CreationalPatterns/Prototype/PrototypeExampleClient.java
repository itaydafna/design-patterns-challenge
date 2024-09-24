package CreationalPatterns.Prototype;

import java.util.HashMap;
import java.util.Map;

enum Size {
    Large,
    Medium,
    Small
}

abstract class Toy {
    final String name;
    final Size size;
    final String material;

    public Toy(String name, Size size, String material) {
        this.name = name;
        this.size = size;
        this.material = material;
    }

    public Toy(Toy target) {
        this.name = target.name;
        this.size = target.size;
        this.material = target.material;
    }

    public abstract Toy clone();

    public abstract Toy cloneWith(String name, Size size, String material);

    public Toy withName(String name){
        return cloneWith(name, this.size, this.material);
    }

    public Toy withSize(Size size){
        return cloneWith(this.name, size, this.material);
    }

    public Toy withMaterial(String material){
        return cloneWith(this.name, this.size, material);
    }

    @Override
    public boolean equals(Object target) {

        return target instanceof Toy targetToy &&
                targetToy.name.equals(this.name) &&
                targetToy.size == this.size &&
                targetToy.material.equals(this.material);
    }

}

class ActionFigure extends Toy {

    final String weapon;

    public ActionFigure(String name, Size size, String material, String weapon) {
        super(name, size, material);
        this.weapon = weapon;
    }

    public ActionFigure(ActionFigure target) {
        super(target);
        this.weapon = target.weapon;
    }


    @Override
    public Toy clone() {
        return new ActionFigure(this);
    }

    @Override
    public Toy cloneWith(String name, Size size, String material) {
        return new ActionFigure(name,size,material,this.weapon);
    }

    @Override
    public boolean equals(Object target) {

        return target instanceof ActionFigure targetActionFigure &&
                targetActionFigure.weapon.equals(this.weapon) &&
                super.equals(target);

    }

    public ActionFigure withWeapon(String weapon){
        return new ActionFigure(this.name,this.size,this.material, weapon);
    }
}

class Doll extends Toy {

    private final String outfit;

    public Doll(String name, Size size, String material, String outfit) {
        super(name, size, material);
        this.outfit = outfit;
    }

    public Doll(Doll target) {
        super(target);
        this.outfit = target.outfit;
    }

    @Override
    public Toy clone() {
        return new Doll(this);
    }

    @Override
    public Toy cloneWith(String name, Size size, String material) {
        return new Doll(name,size,material,this.outfit);
    }

    @Override
    public boolean equals(Object target) {

        return target instanceof Doll doll &&
                doll.outfit.equals(this.outfit) &&
                super.equals(target);
    }

    public Doll withOutfit(String outfit){
        return new Doll(this.name,this.size,this.material, outfit);
    }
}

class ToyPrototypeRegistry {
    static String G_I_JOE_PROTOTYPE = "G.I Joe";
    static String TMNT_PROTOTYPE = "Ninja Turtle";
    static String BARBY_PROTOTYPE = "Barby";

    private final Map<String, Toy> registry = new HashMap<>();

    public void init() {
        this.registry.put("G.I Joe", createGIJoePrototype());
        this.registry.put("Ninja Turtle", createTMNTPrototype());
        this.registry.put("Barby", createBarbyPrototype());
    }

    private ActionFigure createGIJoePrototype() {
        return new ActionFigure(G_I_JOE_PROTOTYPE, Size.Small, "Plastic", "Shotgun");
    }

    private ActionFigure createTMNTPrototype() {
        return new ActionFigure(TMNT_PROTOTYPE, Size.Small, "Plastic", "Sword");
    }

    private Doll createBarbyPrototype() {
        return new Doll(BARBY_PROTOTYPE, Size.Medium, "Plastic", "Gown");
    }

    public Toy getToy(String prototypeName) {
        return this.registry.get(prototypeName).clone();
    }
}


public class PrototypeExampleClient {
    public static void main(String[] args) {
        ToyPrototypeRegistry toyPrototypeRegistry = new ToyPrototypeRegistry();
        toyPrototypeRegistry.init();

        Toy gIJoe = toyPrototypeRegistry.getToy(ToyPrototypeRegistry.G_I_JOE_PROTOTYPE);
        Toy barby = toyPrototypeRegistry.getToy(ToyPrototypeRegistry.BARBY_PROTOTYPE);
        Toy tmnt1 = toyPrototypeRegistry.getToy(ToyPrototypeRegistry.TMNT_PROTOTYPE);
        Toy tmnt2 = toyPrototypeRegistry.getToy(ToyPrototypeRegistry.TMNT_PROTOTYPE);

        ActionFigure largeLeonardo = ((ActionFigure) toyPrototypeRegistry.getToy(ToyPrototypeRegistry.TMNT_PROTOTYPE).withName("Leonardo").withSize(Size.Large)).withWeapon("Swords");


        System.out.println("1st Test Case - G.I. Joe prototype compared wit Barby prototype:");
        if (gIJoe != barby) {
            System.out.println("G.I. Joe and Barby are different objects");
            if (!gIJoe.equals(barby)) {
                System.out.println("and they are essentially different");
            } else {
                throw new RuntimeException("G.I. Joe and Barby aren't essentially different.");
            }
        } else {
            throw new RuntimeException("G.I. Joe and Barby aren't different objects.");
        }

        System.out.println("2nd Test Case - 2 TMNT prototypes compared:");
        if (tmnt1 != tmnt2) {
            System.out.println("The 2 compared TMNT prototypes are different objects");
            if (tmnt1.equals(tmnt2)) {
                System.out.println("and they are essentially the same");
            } else {
                throw new RuntimeException("The 2 compared TMNT prototypes aren't essentialy similar");
            }
        } else {
            throw new RuntimeException("The 2 compared TMNT prototypes aren't different objects.");
        }

        System.out.println("3nd Test Case - Created Large Leonardo from TMNT prototype:");
        System.out.println("name: " + largeLeonardo.name);
        System.out.println("size: " + largeLeonardo.size);
        System.out.println("material: " + largeLeonardo.material);
        System.out.println("weapon: " + largeLeonardo.weapon);
    }
}

