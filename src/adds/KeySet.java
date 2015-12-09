package adds;

import java.awt.event.KeyEvent;

/**
 * Created by Vladislav on 19-Sep-15.
 */
public class KeySet {

    public enum Type {
        KEY_SET_TYPE_LETTERS,
        KEY_SET_TYPE_NUMBERS
    }

    private Type type;

    final static int KEY_SET_TYPE_LETTERS_UP = KeyEvent.VK_W;
    final static int KEY_SET_TYPE_LETTERS_DOWN = KeyEvent.VK_S;
    final static int KEY_SET_TYPE_LETTERS_LEFT = KeyEvent.VK_A;
    final static int KEY_SET_TYPE_LETTERS_RIGHT = KeyEvent.VK_D;
    final static int KEY_SET_TYPE_LETTERS_SHOT = KeyEvent.VK_SPACE;

    final static int KEY_SET_TYPE_NUMBERS_UP = KeyEvent.VK_I;
    final static int KEY_SET_TYPE_NUMBERS_DOWN = KeyEvent.VK_K;
    final static int KEY_SET_TYPE_NUMBERS_LEFT = KeyEvent.VK_J;
    final static int KEY_SET_TYPE_NUMBERS_RIGHT = KeyEvent.VK_L;
    final static int KEY_SET_TYPE_NUMBERS_SHOT = KeyEvent.VK_N;

    public int UP;
    public int DOWN;
    public int LEFT;
    public int RIGHT;
    public int SHOT;

    public KeySet(Type type) {

        this.type = type;

        if (type == Type.KEY_SET_TYPE_LETTERS) {
            UP = KEY_SET_TYPE_LETTERS_UP;
            DOWN = KEY_SET_TYPE_LETTERS_DOWN;
            LEFT = KEY_SET_TYPE_LETTERS_LEFT;
            RIGHT = KEY_SET_TYPE_LETTERS_RIGHT;
            SHOT = KEY_SET_TYPE_LETTERS_SHOT;
        }
        if (type == Type.KEY_SET_TYPE_NUMBERS) {
            UP = KEY_SET_TYPE_NUMBERS_UP;
            DOWN = KEY_SET_TYPE_NUMBERS_DOWN;
            LEFT = KEY_SET_TYPE_NUMBERS_LEFT;
            RIGHT = KEY_SET_TYPE_NUMBERS_RIGHT;
            SHOT = KEY_SET_TYPE_NUMBERS_SHOT;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
