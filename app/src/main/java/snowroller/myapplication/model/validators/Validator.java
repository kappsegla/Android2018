package snowroller.myapplication.model.validators;

public interface Validator<T> {
    boolean validate(T obj);
}
