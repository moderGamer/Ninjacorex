package ninja.corex.exceptionbind;

public class ColorNotFoundException extends RuntimeException{
	public ColorNotFoundException(String massges){
		super(massges);
	}
}