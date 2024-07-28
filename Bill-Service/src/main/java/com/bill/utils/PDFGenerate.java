package com.bill.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerate {
	


	private static final org.slf4j.Logger log = LoggerFactory.getLogger(PDFGenerate.class);

	public void generate(Order order,String path) throws FileNotFoundException, DocumentException {
		log.info("in pdf generate -->" + order);
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(path));

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
		Chunk chunk = new Chunk("FoodKart", font);

		document.add(chunk);
		document.add(new Paragraph("\n"));

		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		for (OrderDetails orderDetails : order.getDescription()) {
			addRows(table, orderDetails);
		}

		document.add(table);
		document.add(new Paragraph("\n"));

		PdfPTable priceTable = new PdfPTable(1);
		PdfPCell price = new PdfPCell();
		price.setPhrase(new Phrase("Total Price  : Rs " + String.valueOf(order.getPrice())));
		price.setBorderWidth(0);
		price.setHorizontalAlignment(Element.ALIGN_RIGHT);
		priceTable.addCell(price);
		document.add(priceTable);

		document.close();

	}

	private static void addTableHeader(PdfPTable table) {
		Stream.of("Name", "Quantity", "Price (per qty)").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			header.setPaddingBottom(10.0f);

			table.addCell(header);
		});
	}

	private static void addRows(PdfPTable table, OrderDetails orderDetails) {
		table.addCell(orderDetails.getName());

		PdfPCell qty = new PdfPCell();
		qty.setPhrase(new Phrase(String.valueOf(orderDetails.getQuantity())));
		qty.setHorizontalAlignment(Element.ALIGN_RIGHT);

		table.addCell(qty);

		PdfPCell price = new PdfPCell();
		price.setPhrase(new Phrase(String.valueOf(orderDetails.getPrice())));
		price.setHorizontalAlignment(Element.ALIGN_RIGHT);

		table.addCell(price);
	}
}
