
from PyPDF2 import PdfReader, PdfWriter
import os

def pdfCompress(input):
    reader = PdfReader(input)
    writer = PdfWriter()

    for page in reader.pages:
        page.compress_content_streams()  # Updated method name
        writer.add_page(page)



    internal_storage_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), "processed_audios")

    # Create the directory if it doesn't exist
    os.makedirs(internal_storage_dir, exist_ok=True)

    # Specify the path to the output PDF file
    output_file = os.path.join(internal_storage_dir, "out.pdf")

    with open(output_file, "wb") as f:
        writer.write(f)
        print("Processed file saved as", output_file)
    return output_file

