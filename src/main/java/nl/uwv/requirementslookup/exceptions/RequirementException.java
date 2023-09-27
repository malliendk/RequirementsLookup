package nl.uwv.requirementslookup.exceptions;

public class RequirementException extends Exception{

    public static final String REQUIREMENTS_NOT_TESTED = "Niet alle requirements zijn getest";

    public RequirementException(String message){
        super(message);
    }
}
