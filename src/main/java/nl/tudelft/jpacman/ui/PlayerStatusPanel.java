package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, with the numbered players on top,
 * their respective scores and lives underneath.
 */
public class PlayerStatusPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Map<Player, JLabel> scoreLabels;
    private final Map<Player, JLabel> lifeLabels;

    public static final ScoreFormatter DEFAULT_SCORE_FORMATTER =
        (Player player) -> String.format("Score: %3d", player.getScore());

    public static final LifeFormatter DEFAULT_LIFE_FORMATTER =
        (Player player) -> String.format("Lives: %2d", player.getLives());

    private ScoreFormatter scoreFormatter = DEFAULT_SCORE_FORMATTER;
    private LifeFormatter lifeFormatter = DEFAULT_LIFE_FORMATTER;

    public PlayerStatusPanel(List<Player> players) {
        super();
        assert players != null;

        // Assuming there are 2 rows for each player, one for the score and one for lives.
        setLayout(new GridLayout(players.size() * 2, 1));

        scoreLabels = new LinkedHashMap<>();
        lifeLabels = new LinkedHashMap<>();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            JLabel playerLabel = new JLabel("Player " + (i + 1), JLabel.LEFT);
            JLabel scoreLabel = new JLabel(scoreFormatter.format(player), JLabel.LEFT);
            JLabel lifeLabel = new JLabel(lifeFormatter.format(player), JLabel.LEFT);

            scoreLabels.put(player, scoreLabel);
            lifeLabels.put(player, lifeLabel);

            add(playerLabel);
            add(scoreLabel);
            add(lifeLabel);
        }
    }

    protected void refresh() {
        for (Player player : scoreLabels.keySet()) {
            JLabel scoreLabel = scoreLabels.get(player);
            JLabel lifeLabel = lifeLabels.get(player);

            String score;
            if (!player.isAlive() && player.getLives() == 0) {
                score = "You died. ";
            } else {
                score = scoreFormatter.format(player);
            }

            scoreLabel.setText(score);

            String lives = lifeFormatter.format(player);
            lifeLabel.setText(lives);
        }
    }

    public interface ScoreFormatter {
        String format(Player player);
    }

    public interface LifeFormatter {
        String format(Player player);
    }

    public void setScoreFormatter(ScoreFormatter scoreFormatter) {
        assert scoreFormatter != null;
        this.scoreFormatter = scoreFormatter;
    }

    public void setLifeFormatter(LifeFormatter lifeFormatter) {
        assert lifeFormatter != null;
        this.lifeFormatter = lifeFormatter;
    }
}
