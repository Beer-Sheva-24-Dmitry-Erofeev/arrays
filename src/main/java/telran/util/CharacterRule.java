package telran.util;

import java.util.function.Predicate;

public class CharacterRule {

    public boolean isAllowed;
    public Predicate<Character> predicate;
    public String errorMessage;

    public CharacterRule(boolean isAllowed, Predicate<Character> predicate, String errorMessage) {
        this.isAllowed = isAllowed;
        this.predicate = predicate;
        this.errorMessage = errorMessage;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public boolean test(char ch) {
        return predicate.test(ch);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
