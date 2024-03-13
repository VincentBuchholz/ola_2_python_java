import pandas as pd
import pickle
import argparse

import warnings

def load_model():
    model = None
    with open("/Users/vincentbuchholz/Desktop/SoftSem2/Python/OLA_2/python-implementation-quarkus/src/main/resources/python/nb.pkl", 'rb') as file:
        model = pickle.load(file)
    return model
    
if __name__ == '__main__':
    warnings.filterwarnings("ignore")
    
    parser = argparse.ArgumentParser(description='Prediction of cancer')
    parser.add_argument('--file', help='path to file containing data', required=True)
    parser.add_argument('--index', help='Index of column to predict', required=True)
    
    
    args = parser.parse_args()
    
    file = args.file
    index = int(args.index)
    
    data = pd.read_csv(file,sep=',')
    
    model = load_model()
    
    print(model.predict(data[index-1:index])[0])
    
    