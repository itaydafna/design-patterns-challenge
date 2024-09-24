package CreationalPatterns.FactoryMethod;

interface Document {
    public String getType();
}

class WordDocument implements Document {
    @Override
    public String getType() {
        return "Word Document";
    }
}

class PdfDocument implements Document {
    @Override
    public String getType() {
        return "PDF Document";
    }
}


class WordProcessor extends DocumentProcessor {

    @Override
    Document createDocument() {
        return new WordDocument();
    }
}

class PdfProcessor extends DocumentProcessor {

    @Override
    Document createDocument() {
        return new PdfDocument();
    }
}

enum DocumentType {
    WORD,
    PDF
}

abstract class DocumentProcessor {

    private Document document;


    abstract Document createDocument();

    public void formatContent() {
        if (this.document == null) {
            this.document = createDocument();
        }
        System.out.println("formatting " + document.getType());
    }

    public void saveDocument() {
        if (this.document == null) {
            this.document = createDocument();
        }
        System.out.println("saving " + document.getType());
    }

    public void displayDocument() {
        if (this.document == null) {
            this.document = createDocument();
        }
        System.out.println("displaying " + document.getType());
    }
}

class DocumentProcessorFactory {
    static DocumentProcessor getDocumentProcessor(DocumentType documentType) {
        return switch (documentType) {
            case WORD -> new WordProcessor();
            case PDF -> new PdfProcessor();
        };
    }
}


public class FactoryMethodExampleClient {
    public static void main(String[] args) {
        DocumentProcessor wordProcessor = DocumentProcessorFactory.getDocumentProcessor(DocumentType.WORD);
        DocumentProcessor pdfProcessor = DocumentProcessorFactory.getDocumentProcessor(DocumentType.PDF);

        wordProcessor.formatContent();
        wordProcessor.saveDocument();
        wordProcessor.displayDocument();

        pdfProcessor.formatContent();
        pdfProcessor.saveDocument();
        pdfProcessor.displayDocument();

    }
}
