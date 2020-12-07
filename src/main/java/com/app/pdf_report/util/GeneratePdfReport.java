package com.app.pdf_report.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.pdf_report.model.City;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdfReport {
	private static final Logger logger=LoggerFactory.getLogger(GeneratePdfReport.class);

	public static ByteArrayInputStream citiReport(List<City> cities) {

		Document doc=new Document();

		ByteArrayOutputStream bout=new ByteArrayOutputStream();

		try {
			PdfPTable table=new PdfPTable(3);
			table.setWidthPercentage(60);
			table.setWidths(new int[] {1,3,3});

			Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfPCell hCell;

			hCell=new PdfPCell(new Phrase("Id",font));
			hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hCell);

			hCell=new PdfPCell(new Phrase("NAME",font));
			hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hCell);

			hCell=new PdfPCell(new Phrase("POPULATION",font));
			hCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hCell);

			for(City city:cities) {
				PdfPCell cell;
				cell=new PdfPCell(new Phrase(city.getId()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell=new PdfPCell(new Phrase(city.getName().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				cell=new PdfPCell(new Phrase(String.valueOf(city.getPopulation())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPadding(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(doc, bout);
			doc.open();
			doc.add(table);
			doc.close();


		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR OCCURED:{0}",e);
		}
		return new ByteArrayInputStream(bout.toByteArray());
	}

}
