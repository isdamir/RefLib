package Module.Logic.Tool;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumOnlyDocument extends PlainDocument{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void insertString(int offset, String s, AttributeSet attrSet)throws BadLocationException {
		   if(!s.matches("^\\d+$"))
		   {
			   return;
		   }
		   super.insertString(offset,s,attrSet);
		  }
}
