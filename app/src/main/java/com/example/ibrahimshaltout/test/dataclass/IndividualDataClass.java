package com.example.ibrahimshaltout.test.dataclass;

import java.util.List;

public class IndividualDataClass {


    public String fullName;
    public String email;
    public String phone;
    public String qualificationLevel;
    public String inputSchool;
    public String inputSchoolType;
    public String inputUniversity;
    public String inputcollege;
    public String inputSpecialization ;
    public String inputGrade ;
    public String fieldof_diploma;
    public String fieldof_masters;
    public String fieldof_doctorate;
    public String startYearDate ;
    public String endYearDate ;
    public String inputCompany;
    public String inputPosition;
    public String inputDep;
    public List <String> inputskills;
    public List <String> inputinterest;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getInputskills() {
        return inputskills;
    }

    public void setInputskills(List<String> inputskills) {
        this.inputskills = inputskills;
    }

    public List<String> getInputinterest() {
        return inputinterest;
    }

    public void setInputinterest(List<String> inputinterest) {
        this.inputinterest = inputinterest;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    public List <String> experience ;


    public IndividualDataClass() {
    }

    public IndividualDataClass(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public String getInputSchool() {
        return inputSchool;
    }

    public void setInputSchool(String inputSchool) {
        this.inputSchool = inputSchool;
    }

    public String getInputcollege() {
        return inputcollege;
    }

    public void setInputcollege(String inputcollege) {
        this.inputcollege = inputcollege;
    }




    public void setInputCompany(String inputCompany) {
        this.inputCompany = inputCompany;
    }

    public String getInputPosition() {
        return inputPosition;
    }

    public void setInputPosition(String inputPosition) {
        this.inputPosition = inputPosition;
    }

    public String getInputDep() {
        return inputDep;
    }

    public void setInputDep(String inputDep) {
        this.inputDep = inputDep;
    }



    public String getInputSchoolType() {
        return inputSchoolType;
    }

    public void setInputSchoolType(String inputSchoolType) {
        this.inputSchoolType = inputSchoolType;
    }

    public String getInputUniversity() {
        return inputUniversity;
    }

    public void setInputUniversity(String inputUniversity) {
        this.inputUniversity = inputUniversity;
    }

    public String getInputSpecialization() {
        return inputSpecialization;
    }

    public void setInputSpecialization(String inputSpecialization) {
        this.inputSpecialization = inputSpecialization;
    }

    public String getInputGrade() {
        return inputGrade;
    }

    public void setInputGrade(String inputGrade) {
        this.inputGrade = inputGrade;
    }

    public String getFieldof_diploma() {
        return fieldof_diploma;
    }

    public void setFieldof_diploma(String fieldof_diploma) {
        this.fieldof_diploma = fieldof_diploma;
    }

    public String getFieldof_masters() {
        return fieldof_masters;
    }

    public void setFieldof_masters(String fieldof_masters) {
        this.fieldof_masters = fieldof_masters;
    }

    public String getFieldof_doctorate() {
        return fieldof_doctorate;
    }

    public void setFieldof_doctorate(String fieldof_doctorate) {
        this.fieldof_doctorate = fieldof_doctorate;
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
