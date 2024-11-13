package az.example.online.shopping.domain.handler.comman.abstacts;

public interface CommandHandler<I,O> {
    public O handle(I input);
}
