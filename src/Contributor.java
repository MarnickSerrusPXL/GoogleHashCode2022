import java.util.ArrayList;
import java.util.List;

public class Contributor {
    public String name;
    public ArrayList<Skill> skills;
    public Project current;

    @Override
    public String toString() {
        return "Contributor{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                ", current=" + current +
                '}';
    }

    public Contributor() {
        this.skills = new ArrayList<Skill>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Project getCurrent() {
        return current;
    }

    public void setCurrent(Project current) {
        this.current = current;
    }
}
