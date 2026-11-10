import csv
import random

# Configuration
INPUT_FILE = 'pricing-data-inputs.csv'
OUTPUT_FILE = 'pricing-data-outputs.csv'
TRAIN_FILE = 'pricing_training.csv'
VALIDATION_FILE = 'pricing_validation.csv'
TRAIN_PERCENTAGE = 0.8  # 80% training, 20% validation

# Étape 1 : Lire les deux fichiers et fusionner ligne par ligne
data = []

with open(INPUT_FILE, 'r') as f_inputs, open(OUTPUT_FILE, 'r') as f_outputs:
    reader_inputs = csv.reader(f_inputs)
    reader_outputs = csv.reader(f_outputs)

    for input_row, output_row in zip(reader_inputs, reader_outputs):
        # Concaténer input (7 colonnes) + output (1 colonne)
        merged_row = input_row + output_row
        data.append(merged_row)

# Étape 2 : Mélanger aléatoirement les lignes pour le split
random.shuffle(data)

# Étape 3 : Séparer training et validation
train_size = int(len(data) * TRAIN_PERCENTAGE)
training_data = data[:train_size]
validation_data = data[train_size:]

# Étape 4 : Sauvegarder les deux fichiers
def save_csv(filename, rows):
    with open(filename, 'w', newline='') as f:
        writer = csv.writer(f)
        writer.writerows(rows)

save_csv(TRAIN_FILE, training_data)
save_csv(VALIDATION_FILE, validation_data)

print(f"✅ Fusion terminée.")
print(f"{len(training_data)} lignes dans '{TRAIN_FILE}'")
print(f"{len(validation_data)} lignes dans '{VALIDATION_FILE}'")