package id.co.nds.gadai_2022_07_04.controllers;

public class ControllerGrup {
    public interface PostingNew {}
    public interface GettingAllByCriteria {}
    public interface GettingById {}
    public interface UpdatingById extends RequestMethodById {}
    public interface DeletingById extends RequestMethodById {}
    public interface RequestMethodById {}
}
