import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class puzzle {
    public static void main(String[] args) {
        ArrayList<Contributor> contributors = new ArrayList<Contributor>();
        ArrayList<Project> projects = new ArrayList<Project>();
        try {
            File myObj = new File("c_collaboration.in.txt");
            Scanner myReader = new Scanner(myObj);
            String firstLine = myReader.nextLine();
            int contributorsc = Integer.parseInt(firstLine.split(" ")[0]);
            int projectsc = Integer.parseInt(firstLine.split(" ")[1]);
            int contributorCount = 0;
            int projectCount = 0;
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
                        newProject.names.add(newSkill.getName());
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
        int completedProjects = 0;
        int[] daysLeft = new int[contributors.size()];
        StringBuilder stringBuilder = new StringBuilder();
        for (int days = 0; days < 10000; days++) {
            for(int i = 0; i < projects.size(); i++){
                Project targetProject = projects.get(i);
                ArrayList<Contributor> chosenContributers = new ArrayList<>();
                ArrayList<String> copyNames = new ArrayList<>(targetProject.names);
                int assignedCount = 0;
                for (Skill neededSkill : targetProject.getSkills()){
                    for (Contributor contributor : contributors){
                        if (daysLeft[contributors.indexOf(contributor)] == 0){
                            if (contributor.getSkills().contains(neededSkill)){
                                //assigned
                                chosenContributers.add(contributor);
                                System.out.println(neededSkill);
                                System.out.println(targetProject.names);
                                int namesIndex = targetProject.names.indexOf(neededSkill.name);
                                targetProject.names.set(namesIndex, contributor.name);
                                assignedCount++;


                            }
                        }
                    }
                }
                if(assignedCount != targetProject.skills.size()){
                    targetProject.names = new ArrayList<>(copyNames);
                }else {
                    for (Contributor contributor: chosenContributers) {
                        daysLeft[contributors.indexOf(contributor)] = targetProject.getDuration();
                    }
                    stringBuilder.append(targetProject.name + "\n");
                    for (String name: targetProject.names) {
                        stringBuilder.append(name + " ");
                    }
                    for (int j = 0; j < targetProject.getSkills().size(); j++) {
                        Skill foundSkill = targetProject.skills.get(j);
                        String foundName = targetProject.names.get(j);
                        for (Contributor contributor: chosenContributers) {
                            if(foundName.equals(contributor.name)){
                                for ( Skill contrSkills: contributor.skills) {
                                    if(contrSkills.name.equals(foundSkill.name) && contrSkills.level == foundSkill.level){
                                        contrSkills.level++;
                                    }
                                }
                            }
                        }
                    }
                    stringBuilder.append("\n");
                    projects.remove(targetProject);
                    completedProjects++;

                }
                for (int j = 0; j < daysLeft.length; j++) {
                    if(daysLeft[j] > 0){
                        daysLeft[j] = daysLeft[j] - 1;
                    }
                }
            }
        }
        stringBuilder.insert(0, completedProjects + "\n");
        System.out.println(stringBuilder.toString());

        try {
            FileWriter outputWriter = new FileWriter("outputc.txt");
            outputWriter.write(stringBuilder.toString());
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
