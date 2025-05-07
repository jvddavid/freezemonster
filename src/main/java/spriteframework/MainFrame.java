package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame {

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public MainFrame(String title) {
		setTitle(title);
		setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		add(createBoard());
		setVisible(true);
	}

	protected abstract AbstractBoard createBoard();
}
