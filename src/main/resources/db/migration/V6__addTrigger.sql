
CREATE TRIGGER trigger_recalculate_pages_on_insert
AFTER INSERT ON Pages
FOR EACH ROW EXECUTE FUNCTION recalculate_page_frontend_ids();
