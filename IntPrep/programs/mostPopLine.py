def getLine(points):
    lines = {}
    for f_pt in points:
        for t_pt in points:
            if t_pt != f_pt:
                slope = (t_pt[1] - f_pt[1] ) * 1.0 / (t_pt[0] - f_pt[0])
                intercept = - slope * t_pt[0] + t_pt[1]
                if lines[(slope, intercept) ] == None:
                    lines[(slope, intercept)] = 1
                else:
                    lines[(slope, intercept)] = lines[(slope, intercept)] + 1
    for key in lines.keys():
        print key, lines[(slope, intercept)]