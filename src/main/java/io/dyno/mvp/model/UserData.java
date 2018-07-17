package io.dyno.mvp.model;

public class UserData {

    // public
    String age;
    String weight;
    String gender;
    String country;
    // encrypted
    String workoutData;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWorkoutData() {
        return workoutData;
    }

    public void setWorkoutData(String workoutData) {
        this.workoutData = workoutData;
    }
}
