package spriteframework;

import javax.swing.JFrame;

public abstract class MainFrame extends JFrame {

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public MainFrame(String t) {

		add(createBoard());

		setTitle(t);
		setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// hotspot
	protected abstract AbstractBoard createBoard();

	// public static void main(String[] args) {
	//
	// EventQueue.invokeLater(() -> {
	//
	// MainFrameExtended ex = new MainFrameExtended();
	// });
	// }

}
