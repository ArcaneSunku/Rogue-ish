package arcane.graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] singleRow;
    private BufferedImage[][] multiRow;

    private double time = 0;
    private boolean multi, playing = false;

    private int rows, columns;
    private int frames, step;

    public Animation(int frames) {
        this.frames = frames;

        singleRow = new BufferedImage[frames];
        multi = false;
    }

    public Animation(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        multiRow = new BufferedImage[rows][columns];
        multi = true;
    }

    public void update() {
        if(playing) {
            time++;
        }
    }

    public void step() {
        if(playing) {
            step++;
        }
    }

    public void init(BufferedImage image, int subWidth, int subHeight) {
        if(multi) {
            for(int i = 0; i < columns; i++) {
                for(int j = 0; j < rows; j++) {
                    multiRow[j][i] = image.getSubimage(j * subWidth, i * subHeight, subWidth, subHeight);
                }
            }
        }
        else
        {
            for(int i = 0; i < frames; i++) {
                singleRow[i] = image.getSubimage(i * subWidth, 0, subWidth, subHeight);
            }
        }
    }

    public BufferedImage getCurrentFrame(int row) {
        return multi ? getFrame(row, step) : getFrame(step);
    }

    public BufferedImage getFrame(int step) {
        return singleRow[step];
    }

    public BufferedImage getFrame(int row, int step) {
        return multiRow[row][step];
    }

    public void toggle() { playing = !playing; }

    public int getStep() { return step; }
    public boolean isPlaying() { return playing; }
    public boolean usingMulti() { return multi; }

}
