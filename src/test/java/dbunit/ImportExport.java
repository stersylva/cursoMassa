package dbunit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import br.ce.wcaquino.dao.utils.ConnectionFactory;

public class ImportExport {
	
	//aula 32 - exporta o banco para um arquivo xml
	public static void main(String[] args) throws Exception{
		//exportarBanco();
		importarBanco();
	}
// aula 33

	private static void importarBanco() throws DatabaseUnitException, SQLException, ClassNotFoundException,
			DataSetException, FileNotFoundException {
		DatabaseConnection dbConn = new DatabaseConnection(ConnectionFactory.getConnection());
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSet = builder.build(new FileInputStream("massas" + File.separator + "saidaFiltrada.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
	}

	private static void exportarBanco() throws Exception {
		DatabaseConnection dbConn = new DatabaseConnection(ConnectionFactory.getConnection());
		IDataSet dataSet = dbConn.createDataSet();
		
		DatabaseSequenceFilter databaseSequenceFilter = new DatabaseSequenceFilter(dbConn);
		FilteredDataSet filteredDataSet = new FilteredDataSet(databaseSequenceFilter, dataSet);
		
		FileOutputStream arquivo = new FileOutputStream("massas" + File.separator + "saidaFiltrada.xml");
		//FlatXmlDataSet.write(dataSet, arquivo);
		FlatXmlDataSet.write(filteredDataSet, arquivo);
	}

	

}
