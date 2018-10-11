public class LevelTwo extends Level {

    public LevelTwo(Bomb bomb, Shot shot, Alien alien, int numberOfLevel, int live) {
        super ( bomb, shot, alien, numberOfLevel = 2, live );
    }

    @Override
    void velocidadBomb() {

        bomb.y = bomb.y + 1;


    }

    @Override
    void velocidadAlien() {

        alien.x = alien.x + 1;
        alien.y = alien.y + 1;

    }
}