from sklearn import tree
from subprocess import call
import pandas as pd
import numpy as np
from prettytable import PrettyTable

def getTreePicture(dt, feature_names, outFileName):
    """
    Create tree png using graphviz.

    Args
    ----
    dt -- scikit-learn DecsisionTree.
    feature_names -- list of feature names.
    """
    tempFileName = 'dt.dot'
    outputFileName = outFileName
    
    #Export tree to a .dot file 
    with open(tempFileName, 'w') as f:
        tree.export_graphviz(dt, out_file=f,feature_names=feature_names)
    
    #Convert .dot file to .png
    command = ['dot', '-Tpng', tempFileName, '-o', outputFileName]
    call(command)
    
def getLegibleCode(tree, feature_names):
    left      = tree.tree_.children_left
    right     = tree.tree_.children_right
    threshold = tree.tree_.threshold
    if len(feature_names) == 1:
        features = [feature_names[0]] * len(tree.tree_.feature)
    else:
        features  = [feature_names[i] for i in tree.tree_.feature]
    value = tree.tree_.value

    def recurse(left, right, threshold, features, node, level):
            prefix = ''
            postfix = ''
            for i in range(level):
                prefix +='   '
            if (threshold[node] != -2):
                    print prefix + 'if ( ' + features[node] + ' <= ' + str(threshold[node]) + ' ) {', postfix
                    if left[node] != -1:
                            recurse (left, right, threshold, features,left[node], level + 1)
                    print prefix + "} else if (" + features[node] + ' > ' + str(threshold[node]) + ") {", postfix
                    if right[node] != -1:
                            recurse (left, right, threshold, features,right[node], level + 1)
                    print prefix + "}", postfix
            else:
                    total = value[node][0][0]+ value[node][0][1]
                    val = value[node][0][1] / total
                    print prefix + "Conv Prob","{:.2%}".format(val), 'of', "{:,}".format(total)
                    #print prefix, "0:", "{:,}".format(str(value[node][0][0])), " 1s: ","{:,}".format(str(value[node][0][1]))
                    print prefix, "\t0s:", "{:,}".format(value[node][0][0]), " 1s: ","{:,}".format(value[node][0][1]), postfix
                    

    recurse(left, right, threshold, features, 0, 0)
def test():
    return 1
def printImportance(cols, dt):
    t = PrettyTable(['Variable','Importance'])
    t.align['Variable'] = "l"
    t.align['Importance'] = "r"
    for i in range(len(cols)):
        t.add_row([cols[i], "{:.4f}".format(dt.feature_importances_[i])])
    print t
    print '\n'
