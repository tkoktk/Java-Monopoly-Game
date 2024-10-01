package sustainopoly;

public class Effect {
    private String message;
    private Runnable effect;

    public Effect(String message, Runnable effect) {
        this.message = message;
        this.effect = effect;
    }

    public String getMessage() {
        return message;
    }

    public void applyEffect() {
        effect.run();
    }
}