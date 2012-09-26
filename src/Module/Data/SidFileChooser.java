package Module.Data;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class SidFileChooser {
	/**
	 * SID过滤器
	 */
	public MyFileFilter sidFileFilter = new MyFileFilter(".sid",
			"SID文件 (*.sid)");
	/**
	 * 纯文本过滤器
	 */
	public MyFileFilter txtFileFilter = new MyFileFilter(".txt",
			"Txt文件 (*.txt)");
	/**
	 * qq与sid的文件
	 */
	public MyFileFilter qsidFileFilter = new MyFileFilter(".qsid",
			"Qsid文件 (*.qsid)");
	
	public MyFileFilter qqFileFilter = new MyFileFilter(".qq",
			"qq文件 (*.qq)");
	/**
	 * sid与密码的文件
	 */
	public MyFileFilter msidFileFilter = new MyFileFilter(".msid",
			"Msid文件 (*.msid)");
	private JFileChooser jf = new JFileChooser();

	public JFileChooser getFileChooser() {
		return jf;
	}

	public void AddFileFilter(FileFilter f) {
		jf.addChoosableFileFilter(f);
	}

	public int OpenDialog(Component arg0) {
		return jf.showOpenDialog(arg0);
	}

	public int SaveDialog(Component arg0) {
		return jf.showSaveDialog(arg0);
	}

	public String GetSelectPath() {
		if (jf.getDialogType() == JFileChooser.SAVE_DIALOG) {
			File file = jf.getSelectedFile(); // 获得文件名
			// 获得被选中的过滤器
			MyFileFilter filter = (MyFileFilter) jf.getFileFilter();
			// 获得过滤器的扩展名
			String ends = filter.getEnds();
			File newFile = null;
			if (file.getAbsolutePath().toUpperCase()
					.endsWith(ends.toUpperCase())) {
				newFile = file;
			} else {
				newFile = new File(file.getAbsolutePath() + ends);
			}
			return newFile.getAbsolutePath();
		} else {
			return jf.getSelectedFile().getAbsolutePath();
		}
	}
}

class MyFileFilter extends FileFilter {

	String ends; // 文件后缀

	public String getEnds() {
		return ends;
	}

	public void setEnds(String ends) {
		this.ends = ends;
	}

	String description; // 文件描述文字

	public MyFileFilter(String ends, String description) { // 构造函数
		this.ends = ends; // 设置文件后缀
		this.description = description; // 设置文件描述文字
	}

	@Override
	// 只显示符合扩展名的文件，目录全部显示
	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		String fileName = file.getName();
		if (fileName.toUpperCase().endsWith(this.ends.toUpperCase()))
			return true;
		return false;
	}

	@Override
	// 返回这个扩展名过滤器的描述
	public String getDescription() {
		return this.description;
	}
}
