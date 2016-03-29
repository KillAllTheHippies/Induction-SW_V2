package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jamie on 29/02/16.
 */
public class Inductee implements Serializable {

        private String name, company, jobTitle, supervisor, carReg;
        private String competencies;
        private long dateOfInduction;
        transient BufferedImage photo;
        private static long serialVersionUID = 6065412820542083316L;

        // transient ArrayList<BufferedImage> documents;

    public Inductee(String name, String company, String jobTitle, String supervisor, String carReg,  String competencies, long dateOfInduction )
    {
        this.competencies = competencies;
        this.name = name;
        this.supervisor = supervisor;
        this.company = company;
        this.carReg = carReg;
        this.jobTitle = jobTitle;
        this.dateOfInduction = dateOfInduction;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // write the documents
//        out.writeInt(documents.size()); // how many images are serialized?
//        for (BufferedImage eachImage : documents) {
//            ImageIO.write(eachImage, "png", out); // png is lossless
//        }
        // write the photograph
        ImageIO.write(photo, "png", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
//        final int imageCount = in.readInt();
//        documents = new ArrayList<BufferedImage>(imageCount);
//        for (int i=0; i<imageCount; i++) {
//            documents.add(ImageIO.read(in));
//        }
        photo = ImageIO.read(in);
    }

    @Override
    public String toString() {
        return "name: " +name + "\n Supervisor: " +supervisor + "\n Company: " + company +
                "\nJob Title: " + jobTitle + "\nVehicle Registration: " + carReg +
                "\nCompetencies: " + competencies +
                "\nDate of Induction: " + new Date(this.dateOfInduction);
    }

    /* *************<- GETTERS AND SETTERS ->************
    * ***************************************************
    * ***************************************************/

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
}

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }

    public long getDateOfInduction() {
        return dateOfInduction;
    }

    public void setDateOfInduction(long dateOfInduction) {
        this.dateOfInduction = dateOfInduction;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BufferedImage getPhoto() {
        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

//    public ArrayList<BufferedImage> getDocuments() {
//        return documents;
//    }
//
//    public void setDocuments(ArrayList<BufferedImage> documents) {
//        this.documents = documents;
//    }
}
