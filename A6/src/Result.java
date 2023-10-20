record Result (String para, int cost) {
    Result updatePara (String w) {
        return new Result (" " + w + para, cost);
    }
    Result updateCost(String w, int space) {
        return new Result ("\n" + w + para, cost + space * space * space);
    }
}

