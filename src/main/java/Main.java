import model.Cat;
import model.Person;
import serialize.ObjectSerializer;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Prince",3,15);
        Person person = new Person("David",36,cat);
        person.addHobby("smoking");
        person.addHobby("Programming");

        try {
            System.out.println(ObjectSerializer.objectSerializer(person,1));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
