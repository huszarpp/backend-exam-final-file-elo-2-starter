package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ChessResults {

    private final List<ChessPlayer> chessPlayersList = new ArrayList<>();

    List<String> getCompetitorsNamesFromFile(String fileName){
        readFileToChessPlayersList(fileName);
        chessPlayersList.sort(new SortByOpenPoints());

        return chessPlayersList.stream().map(ChessPlayer::getName).collect(Collectors.toList());
    }

    private class SortByOpenPoints implements Comparator<ChessPlayer> {

        @Override
        public int compare(ChessPlayer chessPlayer1, ChessPlayer chessPlayer2) {
            if (chessPlayer1.getOpenPoints() == chessPlayer2.getOpenPoints()) {
                return chessPlayer1.getName().compareTo(chessPlayer2.getName());
            }

            return chessPlayer2.getOpenPoints() - chessPlayer1.getOpenPoints();
        }
    }

    private void readFileToChessPlayersList(String fileName) {
        chessPlayersList.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] lineArray = line.split(",");
                ChessPlayer chessPlayer = new ChessPlayer(lineArray[0]);
                for (int i = 1; i < lineArray.length; i++) {
                    chessPlayer.addPoints(convertToInt(lineArray[i]));
                }
                chessPlayersList.add(chessPlayer);
                line = bufferedReader.readLine();
            }
        } catch (IOException ioe) {
            System.out.println("File not found!");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException ioe) {
                    System.out.println("At least we tried!");
                }
            }
        }
    }

    private int convertToInt(String points) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(points);
        } catch (IllegalArgumentException iae) {
            System.out.println("Illegal points format!");
        }

        return intValue;
    }
}
