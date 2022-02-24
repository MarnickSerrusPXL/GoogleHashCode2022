import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class puzzle {
    public static void main(String[] args) {
        try {
            File myObj = new File("a_an_example.in.txt");
            Scanner myReader = new Scanner(myObj);
            String firstLine = myReader.nextLine();
            int contributorsc = Integer.parseInt(firstLine.split(" ")[0]);
            int projectsc = Integer.parseInt(firstLine.split(" ")[1]);
            int contributorCount = 0;
            int projectCount = 0;
            ArrayList<Contributor> contributors = new ArrayList<Contributor>();
            ArrayList<Project> projects = new ArrayList<Project>();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if(contributorCount < contributorsc){
                    contributorCount++;
                    Contributor newContributer = new Contributor();
                    newContributer.setName(line.split(" ")[0]);
                    int skillcount = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < skillcount; i++) {
                        line = myReader.nextLine();
                        Skill newSkill = new Skill();
                        newSkill.setName(line.split(" ")[0]);
                        newSkill.setLevel(Integer.parseInt(line.split(" ")[1]));
                        newContributer.getSkills().add(newSkill);
                    }
                    contributors.add(newContributer);
                }
                else if (projectCount < projectsc) {
                    projectCount++;
                    Project newProject = new Project();
                    newProject.setName(line.split(" ")[0]);
                    newProject.setDuration(Integer.parseInt(line.split(" ")[1]));
                    newProject.setScore(Integer.parseInt(line.split(" ")[2]));
                    newProject.setBestBefore(Integer.parseInt(line.split(" ")[3]));
                    int skillcount = Integer.parseInt(line.split(" ")[4]);
                    for (int i = 0; i < skillcount; i++) {
                        line = myReader.nextLine();
                        Skill newSkill = new Skill();
                        newSkill.setName(line.split(" ")[0]);
                        newSkill.setLevel(Integer.parseInt(line.split(" ")[1]));
                        newProject.getSkills().add(newSkill);
                    }
                    projects.add(newProject);
                }

            }
            myReader.close();
            System.out.println(contributors);
            System.out.println(projects);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            FileWriter outputWriter = new FileWriter("output.txt");
            outputWriter.write("solution");
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
