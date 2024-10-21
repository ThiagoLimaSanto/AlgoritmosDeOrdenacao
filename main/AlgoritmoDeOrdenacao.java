package com.mycompany.main;

import java.util.ArrayList;

public class AlgoritmoDeOrdenacao {

    public void bubbleSort(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }

    public void bubbleSortUpgrade(int[] vetor) {
        int trocou = 0;
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocou++;
                }
            }
            if (trocou == 0) {
                break;
            }
        }
    }

    public void insertionSort(int[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            int elemento = vetor[i];
            int j = i - 1;

            while (j >= 0 && vetor[j] > elemento) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = elemento;
        }
    }

    public void insertionSortComLista(int numero, ArrayList lista) {
        lista.add(numero);
        for (int i = 1; i < lista.size(); i++) {
            int elemento = (int) lista.get(i);
            int j = i - 1;

            while (j >= 0 && (int) lista.get(j) > elemento) {
                int aux = (int) lista.get(j);
                lista.set(j + 1, aux);
                j--;
            }
            lista.set(j + 1, elemento);
        }
    }

    public void selectionSort(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int pos_menor = i;
            for (int j = i; j < vetor.length; j++) {
                if (vetor[j] < vetor[pos_menor]) {
                    pos_menor = j;
                }

                int aux = vetor[i];
                vetor[i] = vetor[pos_menor];
                vetor[pos_menor] = aux;
            }
        }
    }

    public void shellSort(int[] vetor) {
        int tamanho = vetor.length;
        int h = 1;

        while (h < tamanho) {
            h = 3 * h + 1;
        }
        while (h > 1) {
            h /= 3;
            for (int i = h; i < tamanho; i++) {
                int temp = vetor[i];
                int j = i - h;
                while (j >= 0 && temp < vetor[j]) {
                    vetor[j + h] = vetor[j];
                    j -= h;
                }
                vetor[j + h] = temp;
            }
        }
    }

    public void mergeSort(int[] vetor, int esq, int dir) {
        if (esq >= dir) {
            return;
        } else {
            int meio = (esq + dir) / 2;

            mergeSort(vetor, esq, meio);
            mergeSort(vetor, meio + 1, dir);

            merge(vetor, esq, meio, dir);
        }
    }

    public void merge(int[] vetor, int esq, int meio, int dir) {
        int[] helper = new int[vetor.length];
        for (int i = esq; i <= dir; i++) {
            helper[i] = vetor[i];
        }
        int i = esq;
        int j = meio + 1;
        int k = esq;

        while (i <= meio && j <= dir) {
            if (helper[i] <= helper[j]) {
                vetor[k] = helper[i];
                i++;
            } else {
                vetor[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= meio) {
            vetor[k] = helper[i];
            i++;
            k++;
        }
    }

    public void quickSort(int[] vetor, int ini, int fim) {
        if (ini < fim) {
            int index_pivot = particionaHoare(vetor, ini, fim);
            quickSort(vetor, ini, index_pivot - 1);
            quickSort(vetor, index_pivot + 1, fim);
        }
    }

    public int particionaHoare(int[] vetor, int ini, int fim) {
        int i = ini + 1;
        int j = fim;
        int pivot = vetor[ini];

        while (i <= j) {
            while (i <= j && vetor[i] <= pivot) {
                i++;
            }

            while (i <= j && vetor[j] > pivot) {
                j = j - 1;
            }

            if (i < j) {
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }

        int temp = vetor[ini];
        vetor[ini] = vetor[j];
        vetor[j] = temp;

        return j;
    }

    public void quickSortDoisPivot(int[] vetor, int ini, int fim) {
        if (ini < fim) {
            int[] indexPivots = particionaDoisPivot(vetor, ini, fim);
            quickSort(vetor, ini, indexPivots[0] - 1);
            quickSort(vetor, indexPivots[0] + 1, indexPivots[1] - 1);
            quickSort(vetor, indexPivots[1] + 1, fim);
        }
    }

    public int[] particionaDoisPivot(int[] vetor, int ini, int fim) {
        if (vetor[ini] > vetor[fim]) {
            int temp = vetor[ini];
            vetor[ini] = vetor[fim];
            vetor[fim] = temp;
        }

        int pivot1 = vetor[ini];
        int pivot2 = vetor[fim];

        int i = ini + 1;
        int j = fim - 1;
        int k = ini + 1;

        while (k <= j) {
            if (vetor[k] < pivot1) {
                int temp = vetor[k];
                vetor[k] = vetor[i];
                vetor[i] = temp;
                i++;
            } else if (vetor[k] >= pivot2) {
                while (vetor[j] > pivot2 && k < j) {
                    j--;
                }
                int temp = vetor[k];
                vetor[k] = vetor[j];
                vetor[j] = temp;
                j--;
                if (vetor[k] < pivot1) {
                    int temp2 = vetor[k];
                    vetor[k] = vetor[i];
                    vetor[i] = temp2;
                    i++;
                }
            }
            k++;
        }
        i--;
        j++;

        int temp1 = vetor[ini];
        vetor[ini] = vetor[i];
        vetor[i] = temp1;

        int temp2 = vetor[fim];
        vetor[fim] = vetor[j];
        vetor[j] = temp2;

        return new int[]{i, j};
    }

    
    public void printVetor(int[] vetor) {
        System.out.print("{");
        for (int i = 0; i < vetor.length - 1; i++) {
            System.out.print(vetor[i] + " ,");
        }
        System.out.println(vetor[vetor.length - 1] + "}");
    }

    public void printLista(ArrayList lista) {
        System.out.print("Lista: ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i) + " ");
        }
    }
}
