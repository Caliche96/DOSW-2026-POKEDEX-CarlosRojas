public class DataFrame {
    private String [][] data;
    private String [] columns;
    private int rows;
    
    /**
     * Crea un DataFrame filtrando únicamente las filas cuya longitud coincide con el numero de columnas
     * manteniendo el invariante
     */
    public DataFrame(String [][] data, String [] columns){
        this.columns = columns;
        int validCount= 0;
        for (String[] row: data){
            if (row != null && row.length == columns. length){
                validCount++;
            }
        }
        
        this.data = new String[validCount][columns.length];
        int idx= 0;
        for (String [] row :data){
            if (row!= null && row.length==columns.length){
                String[] newRow = new String[columns.length];
                for (int i = 0; i < row.length; i++) {
                    newRow[i] = row[i];
        }
        this.data[idx++] = newRow;
        }
        }
        this.rows= validCount;
    }
    
    
    /**
     * Selecciona un subconjunto de filas (por índice) y una sola columna
     * 
     * @param rowIndices    índices de filas a seleccionar
     * @param column        Nombres de la columna a seleccionar
     * @return              Nuevo DataFrame con las filas y columnas
     */
    public DataFrame loc(int [] rows, String columns){
        int colId = -1;
        for(int i=0;i<column.length;i++){
            if (column[i].equals(columns)){
                colId=i;
                break;
            }
        }
        if (colId ==-1) 
        return new DataFrame(new String[0][], new String []{columns});
        
        return null;
    }    
    
    
    /**
     * Selecciona un subconjunto de columnas por nombre
     * 
     * @param values    Nombres de las columnas a conservar
     * @return Nuevo DataFrame con solo las columnas indicadas
     */
    public DataFrame select(String [] values){
        return null;
    }      


    
    /**
     * Concatena este DataFrame con un arreglo de DataFrames.
     * 
     * @param dfs DataFrames a concatenar
     * @param axis 0= concatenar por filas, 1= concatenar por columnas
     * @return Nuevo DataFrame resultado de la concatenación
     */
    public DataFrame concat(DataFrame [] dfs, byte axis){
        return null;
    }

    /**
     * Devuelve la forma final del DataFrame
     * @param rows
     * @param int columns
     */
    public int [] shape(){
        return new int []{rows,columns.length};
    }    
    
   
    /**
     * Retorna las primeras n filas como cadena formateada
     * con columnas alineadas y el índice de fila incluido
     */
    // The columns are aligned, separated by three spaces, and include the index.
    //     Nombre   Edad    Profesion
    // 0    Lucía     28    Ingeniero
    // 1   Carlos     35     Profesor
    // 2      Ana     42       Doctor
    // 3    Jorge     30   Arquitecto
    // 4    Elena     25    Diseñador
    public String head(int rows) {
      return "";
    }
    
    /**
     * Compara este DataFrame con otro por igualdad estructural y de contenido
     */
    public boolean equals(DataFrame df){
        return false;
    }
    
    
    public boolean equals(Object o){
        return equals((DataFrame)o);
    }
}
