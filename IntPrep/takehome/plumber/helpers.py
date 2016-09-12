from sklearn import tree
from subprocess import call
import pandas as pd
import numpy as np
from prettytable import PrettyTable
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestClassifier

def printCrossTab(df,xCol,yCol):
    """
        This function prints counts of rows(tenants) based on values of two specified cols.
    """
    T_T, T_F, F_T, F_F = getCrossTabSums(df,xCol,yCol)
    t = PrettyTable([yCol,'False','True'])
    t.align[yCol] = "l"
    t.align["False"] = "r"
    t.align["True"] = "r"
    
    t.add_row(['False', "{:,}".format(F_F), "{:,}".format(F_T)])
    t.add_row(['True', "{:,}".format(T_F), "{:,}".format(T_T)])
    print '\t\t',xCol
    print t
    print '\n'

def getCrossTabSums(df,xCol,yCol):
    T_T = len(df[(df[yCol]==1) & (df[xCol]==1)])
    T_F = len(df[(df[yCol]==1) & (df[xCol]==0)])
    F_T = len(df[(df[yCol]==0) & (df[xCol]==1)])
    F_F = len(df[(df[yCol]==0) & (df[xCol]==0)])
    return (T_T,T_F,F_T,F_F)


def getRand(row ):
    """
        The getRand function returns a psuedo-random number between 1 and 10. It is recommended that 'random' 
        be seeded before applying this method. The typical use of this method is to label each row with 
        a psuedo-random number.
    """
    return random.randint(1, 10)


def labelSuccess(row, col, threshold):
    """
        The labelSuccess method is used to label the predicted values into 'True','False' based on
        predicted probability of Logistic Regression.
    """
    if row[col] >= threshold:
        return True
    else:
        return False

def totalAndConv(df):
    
    total = len(df.index)
    convRate = df['ConvertedFlag'].sum()*1.0/total
    return total, convRate

def printGroupByTab(df, groupByCol):
    tenantCountCol = 'TenantCount'
    dfNew = df.groupby(groupByCol).TenantId.nunique()
    
    t = PrettyTable([groupByCol,tenantCountCol])
    t.align[groupByCol] = "l"
    t.align[tenantCountCol] = "r"
    
    for i in range(len(dfNew.index)):
        #print
        tenantCount = "{:,}".format(dfNew[i])
        groupByVal = dfNew.index[i]
        #print groupByVal, tenantCount
        t.add_row([groupByVal, tenantCount])
    #print '\t ', tenantCountCol
    print t
    #print dfNew.index[0], dfNew[0]

def plotStackedBarWithTitle(df, groupByCol, legendFlag, titleString):
    customers = df[['TenantId', groupByCol, legendFlag]]
    
    #Group By and count
    customer_group = customers.groupby([groupByCol, legendFlag])
    tc = customer_group.TenantId.nunique()
    tcUnstack = tc.unstack()
    
    #print tcUnstack
    t = PrettyTable([groupByCol,'False','True', 'Total', 'Rate'])
    t.align[groupByCol] = "l"
    t.align['False'] = "r"
    t.align['True'] = "r"
    t.align['Total'] = "r"
    t.align['Rate'] = "r"
    
    for i in range(len(tcUnstack.index)):
        falseCount = tcUnstack[0][i]
        trueCount = tcUnstack[1][i]
        totalCount = falseCount + trueCount
        rate = trueCount *1.0/ totalCount

        falseCountString = "{:,}".format(falseCount)
        trueCountString = "{:,}".format(trueCount)
        totalCountString = "{:,}".format(totalCount)
        rateString = "{:.2%}".format(rate)
        t.add_row([tcUnstack.index[i], falseCountString, trueCountString, totalCountString, rateString])

    print '\t\t  ', legendFlag
    print t

    #print len(tcUnstack.index)
    #Calculate %share
    rowSum = []
    rowSum = tcUnstack[0] + tcUnstack[1]
    tcUnstack[0] = tcUnstack[0]*1.0/rowSum
    tcUnstack[1] = tcUnstack[1]*1.0/rowSum
    tcUnstack = tcUnstack.fillna(value = 0)
    font = {'family': 'arial',
        'color':  'white',
        'weight': 'normal',
        'size': 10,
        }

    #'''
    #Plot the bar graph
    myPlot = tcUnstack.plot(kind='bar',width = 0.7, stacked=True,title=titleString)
    #myPlot.show()
    fig = plt.gcf()
    
    #Set size of the graph
    fig.set_size_inches(18.5/2, 10.5/2)
    
    #Label the graph
    for i, each in enumerate(tcUnstack.index):
        past = 0
        for col in tcUnstack.columns:
            y = tcUnstack.ix[each][col]
            myPlot.text(i-0.15, y+past-y/2, "{:.2%}".format(y), font)
            past = y

def plotStackedBar(df, groupByCol, legendFlag):
    return plotStackedBarWithTitle(df, groupByCol, legendFlag, "Trial Conversion Share")
