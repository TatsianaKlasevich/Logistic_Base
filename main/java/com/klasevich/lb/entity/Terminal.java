package com.klasevich.lb.entity;

public class Terminal {
    private int terminalId;
    private boolean isBusy;

    public Terminal() {
    }

    public Terminal(int terminalId) {
        this.terminalId = terminalId;
    }

    public Terminal(int terminalId, boolean isBusy) {
        this.terminalId = terminalId;
        this.isBusy = isBusy;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        if (terminalId != terminal.terminalId) return false;
        return isBusy == terminal.isBusy;
    }

    @Override
    public int hashCode() {
        int result = terminalId;
        result = 31 * result + (isBusy ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("terminalId=").append(terminalId);
        sb.append(", isLoad=").append(isBusy);
        sb.append('}');
        return sb.toString();
    }
}
