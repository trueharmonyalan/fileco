from PIL import Image
import os

# def imagecompressor(inputimage):
#     print("received input from kotlin", inputimage)
# # Open the image
#     pic = Image.open(inputimage)
#
# # Determine the format of the original image
#     original_format = pic.format
#     internal_storage_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), "processed_Images")
#     os.makedirs(internal_storage_dir, exist_ok=True)
#     output_file = os.path.join(internal_storage_dir, "out.jpeg")
# # Save the image with compression
#     pic.save(output_file, optimize=True, quality=80)
#     with open(output_file, "wb") as f:
#         print("Processed file saved as", output_file)
#     return output_file

def imagecompressor(inputimage, quality):
    quality = int(quality)

    pic = Image.open(inputimage)

    original_format = pic.format

    internal_storage_dir = os.path.join(os.path.dirname(os.path.abspath(__file__)), "processed_Images")
    os.makedirs(internal_storage_dir, exist_ok=True)

    output_file = os.path.join(internal_storage_dir, f"out.{original_format.lower()}")

    if not 0 <= quality <= 100:
        raise ValueError("Invalid quality setting. Quality should be between 0 and 100.")

    pic.save(output_file, optimize=True, quality=quality)

    print("Processed file saved as", output_file)

    return output_file
