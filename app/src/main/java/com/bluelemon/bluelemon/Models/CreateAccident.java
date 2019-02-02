package com.bluelemon.bluelemon.Models;

import java.util.List;

public class CreateAccident{
    private String accidentAreaObservations;
    private Integer accidentCategoryID;
    private String accidentCommentary;
    private String accidentDate;
    private Integer accidentDepartmentID;
    private String accidentFirstAiderName;
    private Integer accidentID;
    private String accidentInvestigationComment;
    private String accidentInvestigationLevel;
    private Integer accidentInvestigationStageID;
    private String accidentInvestigatorUserID;
    private String accidentSiteID;
    private String accidentTypeComments;
    private Integer accidentTypeID;
    private String accidentVenueLocation;
    private String additionalPersons;
    private String causeComments;
    private String competentPersons;
    private String description;
    private Object emergencyServicesCalled;
    private String emergencyServiceComment;
    private String environmentalComments;
    private String environmentalFactorID;
    private String equipmentComments;
    private String firstAidComments;
    private String humanFactorComments;
    private String humanFactorID;
    private String immediateAction;
    private String immediateCause;
    private List<Object> injuredParty = null;
    private String injuredPartyAddress1;
    private String injuredPartyAddress2;
    private String injuredPartyAddress3;
    private String injuredPartyAddress4;
    private String injuredPartyAgeRange;
    private String injuredPartyEmail;
    private String injuredPartyFirstname;
    private String injuredPartyLandline;
    private String injuredPartyMobile;
    private String injuredPartyOccupation;
    private String injuredPartyPostcode;
    private String injuredPartyRelationshipCategoryID;
    private String injuredPartySurname;
    private String injuredPartyTitle;
    private Boolean isConfirmed;
    private Boolean isRiddorIncident;
    private Boolean nearMiss;
    private String organisationFactorComments;
    private String organisationFactorID;
    private String partOfBody;
    private String poBComment;
    private String reportedByAddress1;
    private String reportedByAddress2;
    private String reportedByAddress3;
    private String reportedByAddress4;
    private String reportedByEmail;
    private String reportedByFirstName;
    private String reportedByJobTitle;
    private String reportedByLandline;
    private String reportedByMobile;
    private String reportedByOccupation;
    private String reportedByPostCode;
    private String reportedBySurname;
    private String riskAssessments;
    private String rootCauses;
    private String safeWorkingProcedures;
    private String sideOfBody;
    private String signedOffByUserID;
    private String significantFindings;
    private String similarRisks;
    private String witnesses;
    private String workingConditions;

    public CreateAccident(String accidentAreaObservations, Integer accidentCategoryID, String accidentCommentary, String accidentDate, Integer accidentDepartmentID, String accidentFirstAiderName, Integer accidentID, String accidentInvestigationComment, String accidentInvestigationLevel, Integer accidentInvestigationStageID, String accidentInvestigatorUserID, String accidentSiteID, String accidentTypeComments, Integer accidentTypeID, String accidentVenueLocation, String additionalPersons, String causeComments, String competentPersons, String description, Object emergencyServicesCalled, String emergencyServiceComment, String environmentalComments, String environmentalFactorID, String equipmentComments, String firstAidComments, String humanFactorComments, String humanFactorID, String immediateAction, String immediateCause, List<Object> injuredParty, String injuredPartyAddress1, String injuredPartyAddress2, String injuredPartyAddress3, String injuredPartyAddress4, String injuredPartyAgeRange, String injuredPartyEmail, String injuredPartyFirstname, String injuredPartyLandline, String injuredPartyMobile, String injuredPartyOccupation, String injuredPartyPostcode, String injuredPartyRelationshipCategoryID, String injuredPartySurname, String injuredPartyTitle, Boolean isConfirmed, Boolean isRiddorIncident, Boolean nearMiss, String organisationFactorComments, String organisationFactorID, String partOfBody, String poBComment, String reportedByAddress1, String reportedByAddress2, String reportedByAddress3, String reportedByAddress4, String reportedByEmail, String reportedByFirstName, String reportedByJobTitle, String reportedByLandline, String reportedByMobile, String reportedByOccupation, String reportedByPostCode, String reportedBySurname, String riskAssessments, String rootCauses, String safeWorkingProcedures, String sideOfBody, String signedOffByUserID, String significantFindings, String similarRisks, String witnesses, String workingConditions) {
        this.accidentAreaObservations = accidentAreaObservations;
        this.accidentCategoryID = accidentCategoryID;
        this.accidentCommentary = accidentCommentary;
        this.accidentDate = accidentDate;
        this.accidentDepartmentID = accidentDepartmentID;
        this.accidentFirstAiderName = accidentFirstAiderName;
        this.accidentID = accidentID;
        this.accidentInvestigationComment = accidentInvestigationComment;
        this.accidentInvestigationLevel = accidentInvestigationLevel;
        this.accidentInvestigationStageID = accidentInvestigationStageID;
        this.accidentInvestigatorUserID = accidentInvestigatorUserID;
        this.accidentSiteID = accidentSiteID;
        this.accidentTypeComments = accidentTypeComments;
        this.accidentTypeID = accidentTypeID;
        this.accidentVenueLocation = accidentVenueLocation;
        this.additionalPersons = additionalPersons;
        this.causeComments = causeComments;
        this.competentPersons = competentPersons;
        this.description = description;
        this.emergencyServicesCalled = emergencyServicesCalled;
        this.emergencyServiceComment = emergencyServiceComment;
        this.environmentalComments = environmentalComments;
        this.environmentalFactorID = environmentalFactorID;
        this.equipmentComments = equipmentComments;
        this.firstAidComments = firstAidComments;
        this.humanFactorComments = humanFactorComments;
        this.humanFactorID = humanFactorID;
        this.immediateAction = immediateAction;
        this.immediateCause = immediateCause;
        this.injuredParty = injuredParty;
        this.injuredPartyAddress1 = injuredPartyAddress1;
        this.injuredPartyAddress2 = injuredPartyAddress2;
        this.injuredPartyAddress3 = injuredPartyAddress3;
        this.injuredPartyAddress4 = injuredPartyAddress4;
        this.injuredPartyAgeRange = injuredPartyAgeRange;
        this.injuredPartyEmail = injuredPartyEmail;
        this.injuredPartyFirstname = injuredPartyFirstname;
        this.injuredPartyLandline = injuredPartyLandline;
        this.injuredPartyMobile = injuredPartyMobile;
        this.injuredPartyOccupation = injuredPartyOccupation;
        this.injuredPartyPostcode = injuredPartyPostcode;
        this.injuredPartyRelationshipCategoryID = injuredPartyRelationshipCategoryID;
        this.injuredPartySurname = injuredPartySurname;
        this.injuredPartyTitle = injuredPartyTitle;
        this.isConfirmed = isConfirmed;
        this.isRiddorIncident = isRiddorIncident;
        this.nearMiss = nearMiss;
        this.organisationFactorComments = organisationFactorComments;
        this.organisationFactorID = organisationFactorID;
        this.partOfBody = partOfBody;
        this.poBComment = poBComment;
        this.reportedByAddress1 = reportedByAddress1;
        this.reportedByAddress2 = reportedByAddress2;
        this.reportedByAddress3 = reportedByAddress3;
        this.reportedByAddress4 = reportedByAddress4;
        this.reportedByEmail = reportedByEmail;
        this.reportedByFirstName = reportedByFirstName;
        this.reportedByJobTitle = reportedByJobTitle;
        this.reportedByLandline = reportedByLandline;
        this.reportedByMobile = reportedByMobile;
        this.reportedByOccupation = reportedByOccupation;
        this.reportedByPostCode = reportedByPostCode;
        this.reportedBySurname = reportedBySurname;
        this.riskAssessments = riskAssessments;
        this.rootCauses = rootCauses;
        this.safeWorkingProcedures = safeWorkingProcedures;
        this.sideOfBody = sideOfBody;
        this.signedOffByUserID = signedOffByUserID;
        this.significantFindings = significantFindings;
        this.similarRisks = similarRisks;
        this.witnesses = witnesses;
        this.workingConditions = workingConditions;
    }
}