package com.example.ibrahimshaltout.test.dataclass;

import android.widget.ScrollView;

import java.sql.Time;
import java.util.List;

public class IndividualDataClass {


    public String user_name;
    public String verified;
    public String search_name;
    public Long created_at;
    private String user_image;
    private String user_status;
    private String user_thumb_image;

    public String email;
    public String phone;
    public String qualificationLevel;
    public String schoolName;
    public String schoolType;
    public String universityName;
    public String collegeName;
    public String depSpecialization;
    public String grade;
    public String diplomaField;
    public String masterField;
    public String doctorateField;
    public String startYearDate;
    public String endYearDate;
    public String companyName;
    public String jobTitle;
    public String department;
    public List<String> skillsList;
    public List<String> interestsList;
    public String imageURL;


    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getUser_thumb_image() {
        return user_thumb_image;
    }

    public void setUser_thumb_image(String user_thumb_image) {
        this.user_thumb_image = user_thumb_image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }
    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public String getSearch_name() {
        return search_name;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFullName() {
        return user_name;
    }

    public void setFullName(String fullName) {
        this.user_name = fullName;
    }

    public List<String> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<String> skillsList) {
        this.skillsList = skillsList;
    }

    public List<String> getInterestsList() {
        return interestsList;
    }

    public void setInterestsList(List<String> interestsList) {
        this.interestsList = interestsList;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    public List<String> experience;


    public IndividualDataClass() {
    }

    public IndividualDataClass(String fullName, String email, String phone, String verified) {
        this.user_name = fullName;
        this.email = email;
        this.phone = phone;
        this.verified = verified;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepSpecialization() {
        return depSpecialization;
    }

    public void setDepSpecialization(String depSpecialization) {
        this.depSpecialization = depSpecialization;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDiplomaField() {
        return diplomaField;
    }

    public void setDiplomaField(String diplomaField) {
        this.diplomaField = diplomaField;
    }

    public String getMasterField() {
        return masterField;
    }

    public void setMasterField(String masterField) {
        this.masterField = masterField;
    }

    public String getDoctorateField() {
        return doctorateField;
    }

    public void setDoctorateField(String doctorateField) {
        this.doctorateField = doctorateField;
    }


    public String getStartYearDate() {
        return startYearDate;
    }

    public void setStartYearDate(String startYearDate) {
        this.startYearDate = startYearDate;
    }

    public String getEndYearDate() {
        return endYearDate;
    }

    public void setEndYearDate(String endYearDate) {
        this.endYearDate = endYearDate;
    }


}
