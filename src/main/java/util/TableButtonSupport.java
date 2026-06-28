package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableButtonSupport extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	private final JButton renderButton ;
	private final JButton editButton;
	private final JTable table;
	private final Consumer<Integer> actionRowHandler;
	private Object currentValue;
	
	public TableButtonSupport(JTable table, String buttonText, Consumer<Integer> actionRowHandler) {
		this.table=table;
		this.actionRowHandler=actionRowHandler;
		
		this.renderButton = new JButton(buttonText);
		
		this.editButton=new JButton(buttonText);
		this.editButton.addActionListener(this);
	}
	
	@Override
	public Object getCellEditorValue() {
		
		return currentValue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		fireEditingStopped();
		
		int row=table.getSelectedRow();
		if(row !=-1)
		{
			actionRowHandler.accept(row);
		}		
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		this.currentValue=value;
		return editButton;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return renderButton;
	}

}
